package com.lifesaver.shortnote.async;

import android.os.AsyncTask;
import android.util.Log;

import com.lifesaver.shortnote.data_models.Note;
import com.lifesaver.shortnote.room_persistence.NoteDao;

public class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {
    //logging/ debugging
    private static final String TAG = "InsertAsyncTask";
    private NoteDao mNoteDao;
    public DeleteAsyncTask(NoteDao dao) {
        mNoteDao = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        Log.d(TAG, "doInBackground: thread: " + Thread.currentThread().getName());
        mNoteDao.delete(notes);
        return null;
    }
}
