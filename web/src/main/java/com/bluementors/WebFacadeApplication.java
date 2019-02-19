package com.bluementors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.mentor.search.mentor", "com.bluementors", "com.bluementors.mentor"})
@EnableJpaRepositories(basePackages="com.bluementors")
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class WebFacadeApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebFacadeApplication.class, args);
    }
}
