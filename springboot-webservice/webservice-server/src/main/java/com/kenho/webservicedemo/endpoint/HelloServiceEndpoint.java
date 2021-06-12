package com.kenho.webservicedemo.endpoint;

import com.kenho.webservicedemo.config.WebServiceServerConfig;
import com.kenho.webservicedemo.helloservice.SayHelloRequest;
import com.kenho.webservicedemo.helloservice.SayHelloResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HelloServiceEndpoint {
    @PayloadRoot(namespace = WebServiceServerConfig.targetnameSpace, localPart = "SayHelloRequest")
    @ResponsePayload
    public SayHelloResponse sayhello(@RequestPayload SayHelloRequest request) {
        SayHelloResponse response = new SayHelloResponse();
        response.setResult("君之名："+request.getName());
        return response;
    }
}
