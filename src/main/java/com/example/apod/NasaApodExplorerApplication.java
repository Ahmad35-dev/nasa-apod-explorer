package com.example.apod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NasaApodExplorerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NasaApodExplorerApplication.class, args);
    }
}
