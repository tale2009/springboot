package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Stephanie on 2017/8/19.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class BootApplication {
    public static void main(String args[])
    {
        SpringApplication.run(BootApplication.class,args);
    }
}
