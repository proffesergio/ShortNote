package com.lifesaver.shortnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.lifesaver.shortnote.adapters.NotesRecyclerAdapter;
import com.lifesaver.shortnote.data_models.Note;
import com.lifesaver.shortnote.utils.VerticalSpacingItemDecorator;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener {

    //Ui Components
    private RecyclerView mRecyclerView;


    //vars
    private ArrayList<Note> mNote = new ArrayList<>();
    private NotesRecyclerAdapter mNoteRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        mRecyclerView = findViewById(R.id.recyclerView);

        initRecyclerView();

        insertNotes();

        Toolbar mToolbar = ((Toolbar) findViewById(R.id.notes_toolbar));
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Short Notes");
    }

    private void insertNotes() {
        for (int i = 0; i<100; i++) {

            Note note = new Note();
            note.setTitle("ShortNote Title " + i);
            note.setContent("Content "+ i);
            note.setTimestamp("Jan 2020");

            mNote.add(note);
        }
        mNoteRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);

        mNoteRecyclerAdapter = new NotesRecyclerAdapter(mNote, (NotesRecyclerAdapter.OnNoteListener) this);
        mRecyclerView.setAdapter(mNoteRecyclerAdapter);
    }


    @Override
    public void onNoteClick(int position) {

        Log.d("TAG", "onNoteClick: clicked at position " + position);

    }
}
