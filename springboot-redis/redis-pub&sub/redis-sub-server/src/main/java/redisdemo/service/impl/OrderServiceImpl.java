package redisdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redisdemo.OrderPO;
import redisdemo.mapper.OrderMapper;
import redisdemo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public void insert(String orderJsonStr) {
        OrderPO orderPO =JSONObject.parseObject(orderJsonStr,OrderPO.class);
        orderMapper.insert(orderPO);
        logger.info("异步新增订单成功");
    }
}
