package com.example.mvvm_tutorial.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mvvm_tutorial.DAO.NoteDAO;
import com.example.mvvm_tutorial.Entity.Note;
import com.example.mvvm_tutorial.Room.NoteDatabase;

import java.util.List;

public class NoteRepository {

    private NoteDatabase noteDatabase;
    private NoteDAO noteDAO;

    public NoteRepository(Application application){
        noteDatabase = NoteDatabase.getInstance(application);
        noteDAO = noteDatabase.noteDAO();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void insert(Note note){
        new InsertAsyncTask(noteDAO).execute(note);
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        private InsertAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.insert(notes[0]);
            return null;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void update(Note note){
        new UpdateAsyncTask(noteDAO).execute(note);
    }

    private static class UpdateAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        private UpdateAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.update(notes[0]);
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void delete(Note note){
        new DeleteAsyncTask(noteDAO).execute(note);
    }

    public static class DeleteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        private DeleteAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.delete(notes[0]);
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteAllNotes(){
        new DeleteAllNotesAsyncTask(noteDAO).execute();
    }

    public static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDAO noteDAO;

        private DeleteAllNotesAsyncTask(NoteDAO noteDAO){
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.deletAllNotes();
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public LiveData<List<Note>> getAllNotes(){
        return noteDAO.getAllNotes();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public LiveData<List<Note>> getNotesByTitle(String title){
        return noteDAO.getNotesByTitle(title);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public LiveData<List<Note>> getNotesByPriority(int priority){
        return noteDAO.getNotesByPriority(priority);
    }

}
