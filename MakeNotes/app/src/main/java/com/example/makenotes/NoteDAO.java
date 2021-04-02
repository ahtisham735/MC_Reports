package com.example.makenotes;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDAO {

   @Query("Insert into note (contents) Values('New Note')")
    void create();
   @Query("Select * FROM note")
   List<Note> getAllNotes();
   @Query("Update note SET contents=:contents  WHERE id=:id")
   void save(String contents,int id);
   @Query("Delete FROM note WHERE id=:id ")
   void delete(int id);

}
