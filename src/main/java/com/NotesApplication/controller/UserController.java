package com.NotesApplication.controller;

import com.NotesApplication.database.dto.JwtUtil;
import com.NotesApplication.database.dto.User;
import com.NotesApplication.database.repository.UserRepository;
import com.NotesApplication.database.user.UserLoginResponse;
import com.NotesApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/createUser")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public UserLoginResponse loginUser(@RequestBody User user){
        return userService.loginUser(user);
    }

    @GetMapping("/getUserById")
    public void fetchUser(@RequestParam String userId){
        userRepository.findById(userId);
    }
}
