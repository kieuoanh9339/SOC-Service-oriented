package com.sudo248.socregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SocRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocRegistryApplication.class, args);
    }

}
