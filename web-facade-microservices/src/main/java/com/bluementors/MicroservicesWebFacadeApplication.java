package com.bluementors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.bluementors"})
@EnableFeignClients
@EnableDiscoveryClient
public class MicroservicesWebFacadeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroservicesWebFacadeApplication.class, args);
    }
}
