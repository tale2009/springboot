package redisdemo.service;

import redisdemo.OrderPO;

import java.net.UnknownHostException;
import java.util.List;

public interface PubService {
    public Long send(String note) throws UnknownHostException;

    public List<OrderPO> find(Long orderId);
}
