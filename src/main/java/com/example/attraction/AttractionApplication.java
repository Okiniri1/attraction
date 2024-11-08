package com.example.attraction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.example.attraction.repository")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AttractionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttractionApplication.class, args);
    }
}