package com.medbis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MedicalspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalspringApplication.class, args);

    }

}
