package com.cmae.chairman;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("com.cmae.chairman.mapper")
@CrossOrigin(origins = "http://localhost:8080")
public class ChairmanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChairmanApplication.class, args);
    }
}
