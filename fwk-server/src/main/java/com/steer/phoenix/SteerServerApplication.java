package com.steer.phoenix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan("com.steer.phoenix.mapper")
public class SteerServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SteerServerApplication.class, args);
    }

}
