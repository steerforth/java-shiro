package com.steer.phoenix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication()
@MapperScan("com.steer.phoenix.modular.system.mapper")
@Profile("!test")
public class SteerServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SteerServerApplication.class, args);
    }

}
