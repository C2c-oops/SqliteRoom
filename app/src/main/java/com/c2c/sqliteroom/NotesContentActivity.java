package com.c2c.sqliteroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.c2c.sqliteroom.models.Note;

public class NotesContentActivity extends AppCompatActivity implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private static final String TAG = "NotesContentActivity";

    //ui components
    private LinedEditText mLinedEditText;
    private TextView mViewTitle;
    private EditText mEditTitle;

    //vars
    private boolean mIsNewNote;
    private Note mCurrentNote;

    private GestureDetector mGestureDetector;

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
        setListeners();
    }

    private void setListeners(){
        mLinedEditText.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this, this);
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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return mGestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.d(TAG, "onDoubleTap: doubled tapped");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }
}