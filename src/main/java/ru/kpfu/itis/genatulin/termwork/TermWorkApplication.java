package ru.kpfu.itis.genatulin.termwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
public class TermWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(TermWorkApplication.class, args);
    }
}
