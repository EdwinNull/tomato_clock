package com.weblearning.tomato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class TomatoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomatoApplication.class, args);
    }

}
