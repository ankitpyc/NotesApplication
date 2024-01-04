package com.NotesApplication.controller;

import com.NotesApplication.database.dto.Note;
import com.NotesApplication.database.dto.SharedNotes;
import com.NotesApplication.database.repository.NoteRepository;
import com.NotesApplication.database.repository.SharingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShareController {

    @Autowired
    private SharingRepository sharingRepository;

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/getSharedNotesUser")
    public List<Note> getSharedNotes(@RequestParam String userId){
        List<SharedNotes> sharedNotes = sharingRepository.findBySharedWithUserId(userId);
        List<String> notesId = sharedNotes.stream().map(sharedNotes1 -> sharedNotes1.getNoteId()).collect(Collectors.toList());
        return noteRepository.findAllById(notesId);
    }

    @PostMapping("/shareNote")
    public void getSharedNotes(@RequestBody SharedNotes sharedNotes){
         sharingRepository.save(sharedNotes);
    }
}
