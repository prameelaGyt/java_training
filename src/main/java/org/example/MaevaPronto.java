package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "org.example")
@EnableJpaRepositories(basePackages = {"org.example.api","org.example.api2"})
@EntityScan(basePackages = {"org.example.api","org.example.api2"})
public class MaevaPronto
{
    public static void main(String[] args) {
        SpringApplication.run(MaevaPronto.class, args);
    }
}
