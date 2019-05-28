package com.example.mvvm_tutorial.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvm_tutorial.Entity.Note;
import com.example.mvvm_tutorial.Repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;


    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }

    public void insert(Note note) {
        noteRepository.insert(note);
    }

    public void update(Note note) {
        noteRepository.update(note);
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }

    public void deleteAllNotes() {
        noteRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return noteRepository.getAllNotes();
    }

    public LiveData<List<Note>> getNotesByTitle(String title){
        return noteRepository.getNotesByTitle(title);
    }

    public LiveData<List<Note>> getNotesByPriority(int priority){
        return noteRepository.getNotesByPriority(priority);
    }

}
