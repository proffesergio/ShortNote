package com.lifesaver.shortnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.lifesaver.shortnote.data_models.Note;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        if(getIntent().hasExtra("Selected_Note")) {

            Note note = getIntent().getParcelableExtra("Selected_Note");
            Log.d("TAG", "onCreate: " + note.toString());
        }


        //NOTE VIEW STATE



        //NOTE EDIT STATE
    }
}
