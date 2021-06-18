package redisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("redisdemo.mapper")
@SpringBootApplication
public class PubApplication {
    public static void main(String args[])
    {
        SpringApplication.run(PubApplication.class,args);
    }
}
