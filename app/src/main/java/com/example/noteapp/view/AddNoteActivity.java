package com.example.noteapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noteapp.databinding.ActivityAddNoteBinding;
import com.example.noteapp.model.Note;
import com.example.noteapp.viewmodel.NoteViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {
    private ActivityAddNoteBinding activityAddNoteBinding;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Note selectedNote;
    private MaterialButton saveBth;
    private MaterialButton deleteBtn;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddNoteBinding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(activityAddNoteBinding.getRoot());
        initWidgets();
        setupViewModel();
        checkForEditNote();
        setSaveButtonClickListener();
        setDeleteButtonClickListener();
    }

    private void initWidgets() {
        titleEditText = activityAddNoteBinding.titleInput;
        descriptionEditText = activityAddNoteBinding.descriptionInput;
        saveBth = activityAddNoteBinding.saveButton;
        deleteBtn = activityAddNoteBinding.deleteButton;
    }

    private void checkForEditNote() {
        Intent previousIntent = getIntent();

        int passedNoteID = previousIntent.getIntExtra(Note.NOTE_EDIT_EXTRA, -1);
        selectedNote = Note.getById(passedNoteID);

        if (selectedNote != null) {
            titleEditText.setText(selectedNote.getTitle());
            descriptionEditText.setText(selectedNote.getDescription());
        } else {
            deleteBtn.setVisibility(View.INVISIBLE);
        }
    }
    private void setupViewModel() {
        noteViewModel = new NoteViewModel(this.getApplication());
    }

    private void setSaveButtonClickListener() {
        saveBth.setOnClickListener(v -> {
            String title = String.valueOf(titleEditText.getText());
            String description = String.valueOf(descriptionEditText.getText());

            if (selectedNote == null) {
                int id = Note.notes.size();
                Note newNote = new Note(id, title, description);
                Note.notes.add(newNote);
                noteViewModel.addNote(newNote);
                Toast.makeText(
                        getApplicationContext(),
                        "Note saved",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                selectedNote.setTitle(title);
                selectedNote.setDescription(description);
                noteViewModel.updateNote(selectedNote);
            }
            finish();
        });
    }

    private void setDeleteButtonClickListener() {
        deleteBtn.setOnClickListener(v -> {
            selectedNote.setDeleted(new Date());
            noteViewModel.updateNote(selectedNote);
            finish();
        });
    }
}