package com.lifesaver.shortnote.room_persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.lifesaver.shortnote.async.DeleteAsyncTask;
import com.lifesaver.shortnote.async.InsertAsyncTask;
import com.lifesaver.shortnote.async.UpdateAsyncTask;
import com.lifesaver.shortnote.data_models.Note;

import java.util.List;

public class NoteRepository {
    private NoteDatabase mNoteDatabase;

    public NoteRepository(Context context) {
        mNoteDatabase = NoteDatabase.getInstance(context);
    }

    //methods to retrieve data via NoteDao interface
    public void insertNote(Note note) {
        new InsertAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
    }

    public void updateNote(Note note) {
        new UpdateAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
    }

    public void deleteNote(Note note) {
        new DeleteAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
    }

    public LiveData<List<Note>> retrieveNotesTask() {
        return mNoteDatabase.getNoteDao().getNotes();
    }
}
