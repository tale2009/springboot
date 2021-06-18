package redisdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redisdemo.OrderPO;
import redisdemo.mapper.OrderMapper;
import redisdemo.service.PubService;
import redisdemo.util.IDWorker;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

@Service
public class PubServiceImpl implements PubService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Long send(String note) throws UnknownHostException {
        Date currentDate=new Date();
        OrderPO orderPO=new OrderPO();
        orderPO.setOrderId(IDWorker.getInstance("eshop_order").nextId());
        orderPO.setNotes(note);
        orderPO.setTotalPrice(BigDecimal.ZERO);
        orderPO.setStatus("");
        orderPO.setPaymentTime(currentDate);
        orderPO.setShipTime(currentDate);
        orderPO.setDealTime(currentDate);
        orderPO.setCreateTime(currentDate);
        orderPO.setModifyTime(currentDate);
        orderPO.setModifyUserId(1L);
        orderPO.setCreateUserId(1L);
        stringRedisTemplate.convertAndSend("java02",JSONObject.toJSONString(orderPO));
        return orderPO.getOrderId();
    }

    @Override
    public List<OrderPO> find(Long orderId) {
        return orderMapper.find(orderId);
    }
}
