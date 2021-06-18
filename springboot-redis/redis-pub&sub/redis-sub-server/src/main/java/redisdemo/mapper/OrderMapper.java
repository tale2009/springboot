package redisdemo.mapper;

import org.springframework.stereotype.Repository;
import redisdemo.OrderPO;

import java.util.List;

@Repository
public interface OrderMapper {
    public void insert(OrderPO orderPO);
}
