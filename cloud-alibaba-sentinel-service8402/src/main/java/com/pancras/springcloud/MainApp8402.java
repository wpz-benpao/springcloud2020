package com.pancras.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MainApp8402 {
    public static void main(String[] args) {
        SpringApplication.run(MainApp8402.class,args);
    }
}
