package com.c2c.sqliteroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.c2c.sqliteroom.models.Note;

public class NotesContentActivity extends AppCompatActivity {

    private static final String TAG = "NotesContentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_content);

        if(getIntent().hasExtra("Selected_note")) {
            Note note = getIntent().getParcelableExtra("Selected_note");
            Log.d(TAG, "onCreate: Note Content" + note.toString());
        }
    }
}