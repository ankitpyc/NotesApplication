package com.NotesApplication.database.repository;

import com.NotesApplication.database.dto.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByUserId(String userId);
    // Additional query methods can be added based on your requirements
}

