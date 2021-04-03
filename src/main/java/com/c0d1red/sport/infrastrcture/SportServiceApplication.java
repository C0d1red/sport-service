package com.c0d1red.sport.infrastrcture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.c0d1red.sport")
@EnableJpaRepositories("com.c0d1red.sport")
@EntityScan("com.c0d1red.sport")
public class SportServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportServiceApplication.class, args);
    }

}
