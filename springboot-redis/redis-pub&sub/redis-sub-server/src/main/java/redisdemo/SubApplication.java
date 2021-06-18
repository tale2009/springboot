package redisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("redisdemo.mapper")
@SpringBootApplication
public class SubApplication {
    public static void main(String args[])
    {
        SpringApplication.run(SubApplication.class,args);
    }
}
