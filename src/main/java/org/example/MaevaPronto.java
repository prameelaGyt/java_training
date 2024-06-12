package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "org.example")
@EnableJpaRepositories(basePackages = {"org.example.api","org.example.api2","org.example.api3"})
@EntityScan(basePackages = {"org.example.api","org.example.api2","org.example.api3"})
@EnableTransactionManagement
public class MaevaPronto
{
    public static void main(String[] args) {
        SpringApplication.run(MaevaPronto.class, args);
    }
}