package com.example.smartairportsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.smartairportsystem.mapper")
public class SmartairportsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartairportsystemApplication.class, args);
    }

}
