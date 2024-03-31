package com.foodhero.association.associationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AssociationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssociationServiceApplication.class, args);
    }

}
