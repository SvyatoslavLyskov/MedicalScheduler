package com.ttt.MedicalScheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MedicalSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalSchedulerApplication.class, args);
    }

}
