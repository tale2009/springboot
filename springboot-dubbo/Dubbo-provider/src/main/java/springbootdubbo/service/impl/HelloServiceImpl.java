package springbootdubbo.service.impl;

import org.apache.dubbo.config.annotation.Service;
import springbootdubbo.HelloService;

@Service
public class HelloServiceImpl  implements HelloService {

    @Override
    public String sayHello(String name) {
        return "你好,"+name;
    }
}
