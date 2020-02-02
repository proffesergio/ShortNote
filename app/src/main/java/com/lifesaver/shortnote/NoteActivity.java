package com.lifesaver.shortnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lifesaver.shortnote.data_models.Note;

public class NoteActivity extends AppCompatActivity implements
        View.OnTouchListener, GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener, View.OnClickListener {

    private static final String TAG = "NoteActivity";

    //two possible states(EDIT/VIEW)
    private static final int EDIT_MODE_ENABLED = 0;
    private static final int EDIT_MODE_DISABLED = 1;

    /**ui components **/
    private LinedEditText mLinedEditText;
    private EditText mEditTitle;
    private TextView mViewTitle;

    private RelativeLayout mCheckContainer, mBackArrowContainer;
    private ImageButton mCheck, mBackArrow;

    /**vars **/
    private boolean mIsNewNote;

    //setting global Note object
    private Note mInitialNote;

    //detecting Gestures [DoubleTap]
    private GestureDetector mGestureDetector;

    private int mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mLinedEditText = findViewById(R.id.note_text);
        mEditTitle = findViewById(R.id.note_edit_title);
        mViewTitle = findViewById(R.id.note_text_title);

        mCheck = findViewById(R.id.toolbar_check);
        mBackArrow = findViewById(R.id.toolbar_arrow);
        mCheckContainer = findViewById(R.id.toolbar_check_container);
        mBackArrowContainer = findViewById(R.id.toolbar_backarrow_container);


        if(getIntent().hasExtra("Selected_Note")) {

            Note note = getIntent().getParcelableExtra("Selected_Note");
            Log.d("TAG", "onCreate: " + note.toString());
        }

        if(getIncomingIntent()) {
            //this is a NEW note, so goto EDIT_MODE
            setNewNoteProperties();
            enableEditMode();
        }
        else {
            //this is NOT a new note, goto VIEW_MODE
            setNoteProperties();

        }
        //calling setTouchListeners [GestureDetector]
        setTouchListeners();


    }

    //method to detect gestures
    private void setTouchListeners() {
        mViewTitle.setOnClickListener(this);
        mCheck.setOnClickListener(this);

        mLinedEditText.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this, this);
    }

    //method to check if it is a new note or not
    private boolean getIncomingIntent() {
        if(getIntent().hasExtra("Selected_Note")) {

            mInitialNote = getIntent().getParcelableExtra("Selected_Note");
            Log.d(TAG, "getIncomingIntent" + mInitialNote.toString());

            //returns true if it is a new note, otherwise return false
            mMode = EDIT_MODE_DISABLED;
            mIsNewNote = false;
            return false;
        }
        else {
            mMode = EDIT_MODE_ENABLED;
            mIsNewNote = true;
            return true;
        }


    }

    //instantiate and set NoteProperties of the new note inside this method
    private void setNewNoteProperties() {

        //set the properties if it is a new note
        mViewTitle.setText("Note Title");           //view_mode
        mEditTitle.setText("Edit Note Title");      //edit_mode


    }

    //setting the NoteProperties if it not a new note
    private void setNoteProperties() {
        mViewTitle.setText(mInitialNote.getTitle());
        mEditTitle.setText(mInitialNote.getTitle());
        mLinedEditText.setText(mInitialNote.getContent());
    }


    /**OnGestureListener Methods**/
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        Log.d(TAG, "Double Tapped");
        enableEditMode();

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    private void enableEditMode() {
        mBackArrowContainer.setVisibility(View.GONE);
        mCheckContainer.setVisibility(View.VISIBLE);

        mViewTitle.setVisibility(View.GONE);
        mEditTitle.setVisibility(View.VISIBLE);

        mMode = EDIT_MODE_ENABLED;
    }
    private void disableEditMode() {
        mBackArrowContainer.setVisibility(View.VISIBLE);
        mCheckContainer.setVisibility(View.GONE);

        mViewTitle.setVisibility(View.VISIBLE);
        mEditTitle.setVisibility(View.GONE);

        mMode = EDIT_MODE_DISABLED;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.toolbar_check: {
                Log.d(TAG, "Clicked on CheckMark");
                disableEditMode();
                mViewTitle.requestFocus();
                break;
            }
            case R.id.note_text_title: {
                enableEditMode();
                mEditTitle.requestFocus();
                mEditTitle.setSelection(mEditTitle.length());
                break;
            }
        }
    }
}
