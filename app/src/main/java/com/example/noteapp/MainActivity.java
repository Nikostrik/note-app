package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noteapp.model.Note;
import com.example.noteapp.view.AddNoteActivity;
import com.example.noteapp.view.NoteAdapter;
import com.example.noteapp.viewmodel.NoteViewModel;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private MaterialButton newNoteBtn;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        setupViewModel();
        loadFromDBToMemory();
        setNoteAdapter();
        addNewNoteButtonClickListener();
        setOnClickListener();
    }

    private void initWidgets() {
        listView = findViewById(R.id.note_list_view);
        newNoteBtn = findViewById(R.id.new_note_button);
    }

    private void setupViewModel() {
        noteViewModel = new NoteViewModel(this.getApplication());
    }

    private void loadFromDBToMemory() {
        noteViewModel.getAllNotes();
    }

    private void setNoteAdapter() {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), Note.nonDeletedNotes());
        listView.setAdapter(noteAdapter);
    }

    private void setOnClickListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Note selectedNote = (Note) listView.getItemAtPosition(position);
            Intent editNoteIntent = new Intent(getApplicationContext(), AddNoteActivity.class);
            editNoteIntent.putExtra(Note.NOTE_EDIT_EXTRA, selectedNote.getId());
            startActivity(editNoteIntent);
        });
    }

    private void addNewNoteButtonClickListener() {
        newNoteBtn.setOnClickListener(
                v -> startActivity(
                        new Intent(MainActivity.this,
                                AddNoteActivity.class))
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNoteAdapter();
    }
}