package com.example.noteapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noteapp.R;
import com.example.noteapp.model.Note;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(Context context, List<Note> notes) {
        super(context, 0, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_view, parent, false);
        }

        TextView title = convertView.findViewById(R.id.title_output);
        TextView description = convertView.findViewById(R.id.description_output);

        title.setText(note.getTitle());
        description.setText(note.getDescription());

        return convertView;
    }
}
