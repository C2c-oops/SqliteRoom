package com.c2c.sqliteroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.c2c.sqliteroom.adapters.NotesRecyclerAdapter;
import com.c2c.sqliteroom.models.Note;
import com.c2c.sqliteroom.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener {

    private static final String TAG = "NotesListActivity";

    //UI components
    private RecyclerView mRecyclerView;

    //vars
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        mRecyclerView = findViewById(R.id.recycler_view);

        initRecyclerView();
        setDummyData();

        setSupportActionBar((Toolbar) findViewById(R.id.notes_toolbar));
        setTitle("Notes");
    }

    private void setDummyData(){
        for(int i=0; i<10; i++){
            Note note = new Note();
            note.setTitle("title # " + i);
            note.setContent("content for # " + i);
            note.setTimeStamp(i + " Jan 2021");
            mNotes.add(note);
        }
        mNotesRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator spacingItemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(spacingItemDecorator);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);

    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: Clicked note # " + position);

        Intent intent = new Intent(this, NotesContentActivity.class);
        intent.putExtra("selected_note", mNotes.get(position));
        startActivity(intent);

    }
}