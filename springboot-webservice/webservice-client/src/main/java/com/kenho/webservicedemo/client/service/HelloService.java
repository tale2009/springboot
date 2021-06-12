package com.kenho.webservicedemo.client.service;

import com.kenho.webservicedemo.helloservice.SayHelloRequest;
import com.kenho.webservicedemo.helloservice.SayHelloResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class HelloService extends WebServiceGatewaySupport {
    public static final String WEB_SERVICES_URI = "http://127.0.0.1:9527/webservice";

    public SayHelloResponse sayHello(String name) {
        // 创建请求对象
        SayHelloRequest request = new SayHelloRequest();
        request.setName(name);
        // 执行请求
        return (SayHelloResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}

