package com.NotesApplication.controller;

import com.NotesApplication.database.dto.User;
import com.NotesApplication.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/user")
    public void createUser(@RequestBody User user){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.println("saving user details");
        userRepository.save(user);
    }

    @GetMapping("/api/user/{id}")
    public void fetchUser(@RequestParam String userId){
        System.out.println("saving user details");
        userRepository.findById(userId);
    }
}
