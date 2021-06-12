package com.kenho.webservicedemo.client.controller;

import com.kenho.webservicedemo.client.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebServiceController {
    @Autowired
    private HelloService helloClient;

    @RequestMapping("sayhello")
    public String sayhello(String name)
    {
        return helloClient.sayHello(name).getResult();
    }

}
