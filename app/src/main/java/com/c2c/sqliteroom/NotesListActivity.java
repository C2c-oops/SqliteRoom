package com.c2c.sqliteroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.c2c.sqliteroom.models.Note;

public class NotesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calling empty constructor
        Note note1 = new Note();
        //calling parameterised constructor
        Note note2 = new Note("Some title", "Some content", "Some timeStamp");


    }
}