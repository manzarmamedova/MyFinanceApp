package com.example.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"}) // tüm package’leri tara: entity, controller, service
@EntityScan(basePackages = {"com.example.entity"}) // entity’leri tara
@EnableJpaRepositories(basePackages = {"com.example.repository"}) // repository’leri tara
public class MyFinanceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFinanceAppApplication.class, args);
    }

}
