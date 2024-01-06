package com.NotesApplication.controller;

import com.NotesApplication.database.dto.MongoMapping;
import com.NotesApplication.database.dto.Note;
import com.NotesApplication.database.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController("/api/notes")
public class NotesController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    MongoMapping mongoMapping;

    @GetMapping("/getNotesbyId")
    public Note getNotesByNoteId(@RequestParam String noteId) {
        return noteRepository.findById(noteId).get();
    }

    @GetMapping("/getNotesByUserId")
    public List<Note> getNoteByUserId(@RequestParam String userId) {
        return (List<Note>) noteRepository.findByUserId(userId);
    }

    @PostMapping("/createNotes")
    public ResponseEntity<HttpStatus> createNote(@RequestBody Note note) {
        System.out.println("Created notes for :" + note);
        note.setCreatedAt(new Date());
        note.setUpdatedAt(new Date());

        noteRepository.save(note);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/searchNotes")
    public List<Note> searchNotes(@RequestParam String textSearch) {
        return mongoMapping.queryNotes(textSearch);
    }
}
