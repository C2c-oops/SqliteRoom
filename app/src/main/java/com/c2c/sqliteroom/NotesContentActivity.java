package com.c2c.sqliteroom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.c2c.sqliteroom.models.Note;

public class NotesContentActivity extends AppCompatActivity implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener,
        View.OnClickListener {

    private static final String TAG = "NotesContentActivity";

    private static final int EDIT_MODE_ENABLED = 1;
    private static final int EDIT_MODE_DISABLED = 0;

    //ui components
    private LinedEditText mLinedEditText;
    private TextView mViewTitle;
    private EditText mEditTitle;
    private RelativeLayout mBackContainer;
    private RelativeLayout mDoneContainer;
    private ImageButton mBackBtn;
    private ImageButton mDoneBtn;

    //vars
    private boolean mIsNewNote;
    private Note mCurrentNote;
    private GestureDetector mGestureDetector;
    private int mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_content);

        mLinedEditText = findViewById(R.id.notes_content_text);
        mViewTitle = findViewById(R.id.toolbar_note_view_title);
        mEditTitle = findViewById(R.id.toolbar_note_edit_title);
        mBackContainer = findViewById(R.id.toolbar_back_arrow_container);
        mBackBtn = findViewById(R.id.toolbar_back_arrow_btn);
        mDoneContainer = findViewById(R.id.toolbar_done_container);
        mDoneBtn = findViewById(R.id.toolbar_done_btn);

        if(getIncomingIntent()){
            //new note -> {Edit Mode}
            setNewNoteProperties();
            enableEditMode();
        }
        else{
            //old note -> {View Mode}
            setNoteProperties();
            disableContentInteraction();
        }
        setListeners();
    }

    private void setListeners(){
        mLinedEditText.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this, this);
        mViewTitle.setOnClickListener(this);
        mDoneBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
    }

    public boolean getIncomingIntent(){
        if(getIntent().hasExtra("selected_note")){
            mCurrentNote = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: Note Content" + mCurrentNote.toString());
            mMode = EDIT_MODE_DISABLED;
            mIsNewNote = false;
            return false;
        }
        mMode = EDIT_MODE_ENABLED;
        mIsNewNote = true;
        return true;
    }

    private void enableEditMode(){
        mBackContainer.setVisibility(View.GONE);
        mDoneContainer.setVisibility(View.VISIBLE);
        mViewTitle.setVisibility(View.GONE);
        mEditTitle.setVisibility(View.VISIBLE);

        mMode = EDIT_MODE_ENABLED;
        enableContentInteraction();
    }

    private void disableEditMode(){
        mBackContainer.setVisibility(View.VISIBLE);
        mDoneContainer.setVisibility(View.GONE);
        mViewTitle.setVisibility(View.VISIBLE);
        mEditTitle.setVisibility(View.GONE);

        mMode = EDIT_MODE_DISABLED;
        disableContentInteraction();
    }

    private void enableContentInteraction(){
        mLinedEditText.setKeyListener(new EditText(this).getKeyListener());
        mLinedEditText.setFocusable(true);
        mLinedEditText.setFocusableInTouchMode(true);
        mLinedEditText.setCursorVisible(true);
        mLinedEditText.requestFocus();
    }

    private void disableContentInteraction(){
        mLinedEditText.setKeyListener(null);
        mLinedEditText.setFocusable(false);
        mLinedEditText.setFocusableInTouchMode(false);
        mLinedEditText.setCursorVisible(false);
        mLinedEditText.clearFocus();
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if(view == null){
            view = new View(this);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
        enableEditMode();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toolbar_done_btn:{
                disableEditMode();
                hideSoftKeyboard();
                break;
            }
            case R.id.toolbar_note_view_title:{
                enableEditMode();
                mEditTitle.requestFocus();
                mEditTitle.setSelection(mEditTitle.length());
                break;
            }
            case R.id.toolbar_back_arrow_btn:{
                finish();
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(mMode == EDIT_MODE_ENABLED){
            onClick(mDoneBtn);
        }
        else {
            super.onBackPressed();
        }
    }
}