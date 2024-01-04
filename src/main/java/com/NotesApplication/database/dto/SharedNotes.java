package com.NotesApplication.database.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "sharedNotes")
public class SharedNotes {

    @Id
    private String id;

    private String noteId;
    private String sharedWithUserId;
    private Date createdAt;

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getSharedWithUserId() {
        return sharedWithUserId;
    }

    public void setSharedWithUserId(String sharedWithUserId) {
        this.sharedWithUserId = sharedWithUserId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
// Constructors, getters, setters, etc.
}
