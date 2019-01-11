package com.bluementors.user;

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

    public User register(User user) {
        return userRepository.save(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
