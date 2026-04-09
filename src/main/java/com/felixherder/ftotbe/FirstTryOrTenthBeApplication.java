package com.felixherder.ftotbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FirstTryOrTenthBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstTryOrTenthBeApplication.class, args);
    }

}
