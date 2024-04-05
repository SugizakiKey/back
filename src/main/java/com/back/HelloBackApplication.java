package com.back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(value = {"com.back.dao"})
public class HelloBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloBackApplication.class, args);
    }

}
