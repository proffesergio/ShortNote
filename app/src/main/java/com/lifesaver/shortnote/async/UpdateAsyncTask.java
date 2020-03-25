package com.lifesaver.shortnote.async;

import android.os.AsyncTask;
import android.util.Log;

import com.lifesaver.shortnote.data_models.Note;
import com.lifesaver.shortnote.room_persistence.NoteDao;

public class UpdateAsyncTask extends AsyncTask<Note, Void, Void> {
    //logging/ debugging
    private static final String TAG = "InsertAsyncTask";
    private NoteDao mNoteDao;
    public UpdateAsyncTask(NoteDao dao) {
        mNoteDao = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        Log.d(TAG, "doInBackground: thread: " + Thread.currentThread().getName());
        mNoteDao.update(notes);
        return null;
    }
}
