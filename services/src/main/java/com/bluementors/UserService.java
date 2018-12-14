package com.bluementors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> all(){
      return userRepository.findAll();
    }

    public User findUser(String name){
        return userRepository.findByName(name);
    }

    //TODO: roles allowed ADMIN
    public User register(User user) {
        return userRepository.save(user);
    }

}
