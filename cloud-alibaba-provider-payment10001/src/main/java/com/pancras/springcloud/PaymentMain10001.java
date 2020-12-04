package com.pancras.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain10001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain10001.class,args);
    }
}
