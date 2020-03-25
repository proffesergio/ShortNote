package com.lifesaver.shortnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lifesaver.shortnote.adapters.NotesRecyclerAdapter;
import com.lifesaver.shortnote.data_models.Note;
import com.lifesaver.shortnote.room_persistence.NoteRepository;
import com.lifesaver.shortnote.utils.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener,
        View.OnClickListener {

    //Ui Components
    private RecyclerView mRecyclerView;


    //vars
    private ArrayList<Note> mNote = new ArrayList<>();
    private NotesRecyclerAdapter mNoteRecyclerAdapter;
    private NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        mRecyclerView = findViewById(R.id.recyclerView);
        findViewById(R.id.add_note_fab).setOnClickListener(this);
        mNoteRepository = new NoteRepository(this);

        initRecyclerView();
        retrieveNotes();
//        insertNotes();

        Toolbar mToolbar = ((Toolbar) findViewById(R.id.notes_toolbar));
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Short Notes");
        mToolbar.setTitleTextColor(Color.WHITE);
    }

    private void retrieveNotes() {
        //observer to observe data changes on LiveData object
        mNoteRepository.retrieveNotesTask().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //any changes on LiveData object, will trigger this onChanged method
                if(mNote.size() > 0) {
                    mNote.clear();
                }
                if(notes != null) {
                    mNote.addAll(notes);
                }
                mNoteRecyclerAdapter.notifyDataSetChanged();
            }
        });
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
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        mNoteRecyclerAdapter = new NotesRecyclerAdapter(mNote, (NotesRecyclerAdapter.OnNoteListener) this);
        mRecyclerView.setAdapter(mNoteRecyclerAdapter);
    }


    @Override
    public void onNoteClick(int position) {

        Log.d("TAG", "onNoteClick: clicked at position " + position);

        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("Selected_Note", mNote.get(position));
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);

    }

    private void deleteNote(Note note) {
        mNote.remove(note);
        mNoteRecyclerAdapter.notifyDataSetChanged();;

        mNoteRepository.deleteNote(note);
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNote(mNote.get(viewHolder.getAdapterPosition()));
        }
    };
}
