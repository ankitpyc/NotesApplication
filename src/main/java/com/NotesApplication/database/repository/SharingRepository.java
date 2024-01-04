package com.NotesApplication.database.repository;

import com.NotesApplication.database.dto.SharedNotes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SharingRepository extends MongoRepository<SharedNotes, String> {
    List<SharedNotes> findBySharedWithUserId(String sharedWithUserId);
    // Additional query methods can be added based on your requirements
}

