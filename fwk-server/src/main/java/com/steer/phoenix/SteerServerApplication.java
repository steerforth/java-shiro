package com.steer.phoenix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.steer.phoenix.modular.system.mapper")
public class SteerServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SteerServerApplication.class, args);
    }

}
