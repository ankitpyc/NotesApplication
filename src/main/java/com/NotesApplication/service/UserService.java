package com.NotesApplication.service;

import com.NotesApplication.database.dto.User;
import com.NotesApplication.database.repository.UserRepository;
import com.NotesApplication.database.user.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.NotesApplication.database.dto.*;

@Component
public class UserService implements UserDetailsService {
    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtTokenUtil;

    public User getUser(String userName){
        return userRepository.findByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public ResponseEntity<HttpStatus> createUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public UserLoginResponse loginUser(User user){
        User userDetails = getUser(user.getUsername());
        boolean isPassMatched = passwordEncoder.matches(user.getPassword(),userDetails.getPassword());
        if(isPassMatched){
            String token = jwtTokenUtil.generateToken(user.getUsername());
            return UserLoginResponse.builder().user(user).token(token).build();
        }
        throw new UsernameNotFoundException("User is not valid");
    }

}
