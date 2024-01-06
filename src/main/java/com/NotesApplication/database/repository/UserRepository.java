package com.NotesApplication.database.repository;

import com.NotesApplication.database.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String userName,String password);
}

