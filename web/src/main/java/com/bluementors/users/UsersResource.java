package com.bluementors.users;

import com.bluementors.User;
import com.bluementors.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UsersResource {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public List<User> listAll(){
        return userService.all();
    }


    @SpringBootApplication
    @Configuration
    @ComponentScan(basePackages ={"mentor.search.com", "com.com.mentor.search.users"})
    @EnableJpaRepositories(basePackages = {"mentor.search.com","com.com.mentor.search.users"})
    public static class MentorsearchApplication {

        public static void main(String[] args) {
            SpringApplication.run(MentorsearchApplication.class, args);
        }
    }
}
