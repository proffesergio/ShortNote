package com.lifesaver.shortnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.lifesaver.shortnote.data_models.Note;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity";

    /**ui components **/
    private LinedEditText mLinedEditText;
    private EditText mEditTitle;
    private TextView mViewTitle;

    /**vars **/
    private boolean mIsNewNote;

    //setting global Note object
    private Note mInitialNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mLinedEditText = findViewById(R.id.note_text);
        mEditTitle = findViewById(R.id.note_edit_title);
        mViewTitle = findViewById(R.id.note_text_title);


        if(getIntent().hasExtra("Selected_Note")) {

            Note note = getIntent().getParcelableExtra("Selected_Note");
            Log.d("TAG", "onCreate: " + note.toString());
        }

        if(getIncomingIntent()) {
            //this is a NEW note, so goto EDIT_MODE
            setNewNoteProperties();
        }
        else {
            //this is NOT a new note, goto VIEW_MODE
            setNoteProperties();

        }


    }

    //method to check if it is a new note or not
    private boolean getIncomingIntent() {
        if(getIntent().hasExtra("Selected_Note")) {

            mInitialNote = getIntent().getParcelableExtra("Selected_Note");
            Log.d(TAG, "getIncomingIntent" + mInitialNote.toString());

            //returns true if it is a new note, otherwise return false
            mIsNewNote = false;
            return false;
        }
        else {
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
}
