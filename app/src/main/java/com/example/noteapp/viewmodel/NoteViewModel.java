package com.example.noteapp.viewmodel;

import android.app.Application;

import com.example.noteapp.model.Note;
import com.example.noteapp.repository.NoteRepository;

public class NoteViewModel {
    private NoteRepository repository;

    public NoteViewModel(Application application) {
        super();
        repository = new NoteRepository(application);
    }

    public void addNote(Note note) {
        repository.addNote(note);
    }

    public void getAllNotes() {
        repository.getAllNotes();
    }

    public void updateNote(Note note) {
        repository.updateNote(note);
    }
}
