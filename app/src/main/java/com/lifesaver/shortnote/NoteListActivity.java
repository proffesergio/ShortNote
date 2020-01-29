package com.lifesaver.shortnote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.lifesaver.shortnote.data_models.Note;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
    }

    Note note1 = new Note("title", "content", "timestamp");

    Note note2 = new Note();

}
