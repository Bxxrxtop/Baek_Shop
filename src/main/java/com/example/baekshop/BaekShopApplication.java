package com.example.baekshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@EnableJpaAuditing
public class BaekShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaekShopApplication.class, args);
    }

}
