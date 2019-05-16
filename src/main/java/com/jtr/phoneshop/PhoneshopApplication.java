package com.jtr.phoneshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:beans.xml"})
public class PhoneshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneshopApplication.class, args);
    }

}
