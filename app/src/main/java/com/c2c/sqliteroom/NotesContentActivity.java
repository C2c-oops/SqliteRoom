package com.c2c.sqliteroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.c2c.sqliteroom.models.Note;

public class NotesContentActivity extends AppCompatActivity {

    private static final String TAG = "NotesContentActivity";

    //ui components
    private LinedEditText mLinedEditText;
    private TextView mViewTitle;
    private EditText mEditTitle;

    //vars
    private boolean mIsNewNote;
    private Note mCurrentNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_content);

        mLinedEditText = findViewById(R.id.notes_content_text);
        mViewTitle = findViewById(R.id.toolbar_note_view_title);
        mEditTitle = findViewById(R.id.toolbar_note_edit_title);

        if(getIncomingIntent()){
            //new note -> {Edit Mode}
            setNewNoteProperties();
        }
        else{
            //old note -> {View Mode}
            setNoteProperties();
        }
    }

    public boolean getIncomingIntent(){
        if(getIntent().hasExtra("selected_note")){
            mCurrentNote = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: Note Content" + mCurrentNote.toString());
            mIsNewNote = false;
            return false;
        }
        mIsNewNote = true;
        return true;
    }

    private void setNoteProperties(){
        mViewTitle.setText(mCurrentNote.getTitle());
        mEditTitle.setText(mCurrentNote.getTitle());
        mLinedEditText.setText(mCurrentNote.getContent());
    }

    private void setNewNoteProperties(){
        mViewTitle.setText("Note's Title");
        mEditTitle.setText("Note's Title");
    }
}