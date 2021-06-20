package springbootdubbo.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootdubbo.HelloService;

@RestController
public class HelloController {
    @Reference
    private HelloService helloService;
    @RequestMapping("sayhello")
    public String sayhello(String name)
    {
        return helloService.sayHello(name);
    }
}
