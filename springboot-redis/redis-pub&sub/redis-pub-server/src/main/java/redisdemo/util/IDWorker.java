package redisdemo.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.基于容器环境特质省掉原始算法需要手动初始化的构造器
 * 2.使用map保存每个表的IDWorker实例，id在一定程度上连续
 * 3.去掉一些容器环境下不会出现的异常判断
 */
public class IDWorker {
    /**
     * 根据不同表会有不同表的保存不同表的IDWorker实例保证不同表的ID是有序的
     */
    public static Map<String, IDWorker> workerInstanceMap=new HashMap<String, IDWorker>();


    private long twepoch = 1288834974657L; //起始时间戳，用于用当前时间戳减去这个时间戳，算出偏移量


    private long workerId;
    private long datacenterId;
    private long workerIdBits = 8L; //workerId占用的位数：8
    private long datacenterIdBits = 1L; //datacenterId占用的位数：1
    private long sequenceBits = 13L;//序列号占用的位数：13
    private long workerIdShift = sequenceBits; // 13
    private long datacenterIdShift = sequenceBits + workerIdBits; // 13+8 = 21
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; // 13+8+1 = 22
    private long sequenceMask = -1L ^ (-1L << sequenceBits);//8191

    private long lastTimestamp = -1L;


    private long sequence;

    public IDWorker() throws UnknownHostException {
        /**
         * 看了github上关于k8s pod的讨论在1.8.0修复了相同ip的情况，所以理论上可以使用IP后两段做为区分机器集群
         * 免去了原始雪花算法需要手动设置参数的操作
         */
        /**
         * 这个写法也只适合中小型企业单个IDC的情况下，
         * 如果是本地两、三机房或者异地多活，datacenterId就需要通过获取用户中心标识来赋值
         */
        workerId= Long.parseLong(InetAddress.getLocalHost().getHostAddress().split("\\.")[3]);
        datacenterId=1L;
    }

    /**
     * 核心算法
     * @return
     */
    public synchronized long nextId()
    {
        long timestamp = timeGen();
        //lastTimestamp 由jvm初始化，后面的值赋值见后面代码
        //这里是并发判断不在并发的时候走else
        if (lastTimestamp == timestamp) {
            // 确保sequence + 1之后不会溢出，最大值为8191，其实也就是保证1毫秒内最多生成8191个ID
            //8191L & seqMask=8191
            //8192L & seqMask=0
            sequence = (sequence + 1) & sequenceMask;
            // 如果sequence溢出则变为0，说明1毫秒内并发生成的ID数量超过了4096个
            // 又因为这个方法使用了synchronized，所以4097的数据在这个方法结束之前是进不来的
            // 这个时候同1毫秒的第8192个生成的ID必须等待下一毫秒，
            if (sequence == 0) {
                // 死循环等待下一个毫秒值，直到比lastTimestamp大
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        else {
            sequence = 0L;
        }
        //在推特那个时代可能会出现时钟回拨的时代
        //但在容器时代，在k8s下，可以通过挂载/etc/localtime来使各pod时间一致，因此原始算法timestamp < lastTimestamp是不会进去了
        if (timestamp < lastTimestamp) {
            //所以这里就不写代码了
        }
        lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
        return nextId;
    }

    /**
     * 超过时间内可以提供的id的时候需要等
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    /**
     * 原算法需要多次获取当前时间来进行判断
     * @return
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static synchronized IDWorker getInstance(String tableName) throws UnknownHostException {
        /**
         * 根据不同人的他们不同输入的tableName
         * "" " " "system_user" "sYstem_user" "system_User" "system_user "
         * "" " "可以归纳为同一个key:""
         * "system_user" "sYstem_user" "system_User" "system_user "可以归纳为同一个key:"system_user"
         */
        tableName=tableName.trim().isEmpty()?tableName.trim():tableName.trim().toLowerCase();
        if(workerInstanceMap.get(tableName)==null)
        {
            IDWorker idWorker = new IDWorker();
            workerInstanceMap.put(tableName,idWorker);
            return idWorker;
        }
        else
        {
            return workerInstanceMap.get(tableName);
        }
    }
}