package com.example.mvvm_tutorial.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvm_tutorial.Entity.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deletAllNotes();

    @Query("SELECT * FROM note_table WHERE title LIKE :title")
    LiveData<List<Note>> getNotesByTitle(String title);

    @Query("SELECT * FROM note_table WHERE priority = :priority")
    LiveData<List<Note>> getNotesByPriority(int priority);

    @Query("SELECT * FROM note_table ORDER BY title")
    LiveData<List<Note>> getAllNotes();

}
