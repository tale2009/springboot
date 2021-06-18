package redisdemo.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redisdemo.OrderPO;
import redisdemo.service.PubService;

import java.net.UnknownHostException;
import java.util.List;

@RestController
public class PubController {
    @Autowired
    private PubService pubService;
    @RequestMapping("send")
    public String send(String notes) throws UnknownHostException {
        return "订单id："+pubService.send(notes).toString();
    }

    @RequestMapping("find")
    public List<OrderPO> send(Long orderId) throws UnknownHostException {
        return pubService.find(orderId);
    }
}
