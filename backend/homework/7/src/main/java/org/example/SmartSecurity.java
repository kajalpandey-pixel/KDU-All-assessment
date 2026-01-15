package org.example;

import org.example.service.SmartLockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartSecurity {

    public static void main(String[] args) {
        SpringApplication.run(SmartSecurity.class, args);
    }

    @Bean
    CommandLineRunner run(SmartLockService smartLockService) {
        return args ->  smartLockService.unlock("");

//        {
//            smartLockService.checkBattery();
//
//            smartLockService.unlock("Guest");    // should proceed
//            smartLockService.unlock("Unknown");  // should be blocked (method won't run)
//        };
    }
}
