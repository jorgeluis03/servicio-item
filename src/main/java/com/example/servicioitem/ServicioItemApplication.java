package com.example.servicioitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioItemApplication.class, args);
    }

}
