package com.example.baekshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BaekShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaekShopApplication.class, args);
    }

}
