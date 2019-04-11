package com.dsniatecki.yourfleetmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dsniatecki.yourfleetmanager.repositories")
public class YourFleetManagerRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourFleetManagerRestApplication.class, args);
    }

}
