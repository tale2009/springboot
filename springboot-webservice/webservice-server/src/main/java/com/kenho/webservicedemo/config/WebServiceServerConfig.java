package com.kenho.webservicedemo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs//启动webservice
public class WebServiceServerConfig extends WsConfigurerAdapter {
    public static final String targetnameSpace="https://github.com/tale2009";
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/webservice/*");//服务域名/ws/**
    }

    @Bean
    public XsdSchema helloerviceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("helloservice.xsd"));
    }

    @Bean(name = "sayhello")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema usersSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setLocationUri("/webservice");
        wsdl11Definition.setTargetNamespace(targetnameSpace);
        wsdl11Definition.setSchema(usersSchema);
        wsdl11Definition.setPortTypeName("helloPort");
        return wsdl11Definition;
    }
}

