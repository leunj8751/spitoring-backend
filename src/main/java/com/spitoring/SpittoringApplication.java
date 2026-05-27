package com.spitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpittoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpittoringApplication.class, args);
    }
}
