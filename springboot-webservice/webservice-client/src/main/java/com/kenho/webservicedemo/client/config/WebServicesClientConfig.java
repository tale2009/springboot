package com.kenho.webservicedemo.client.config;

import com.kenho.webservicedemo.client.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WebServicesClientConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.kenho.webservicedemo.helloservice"); // 用户服务的 WSDL 文件
        return marshaller;
    }

    // 构建服务Bean
    @Bean
    public HelloService createClient(Jaxb2Marshaller marshaller) {
        HelloService client = new HelloService();
        client.setDefaultUri(HelloService.WEB_SERVICES_URI); // 用户服务的 Web Services 地址
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}

