package com.example.makenotes;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDAO noteDAO();//will be implemented by room library.that's why,it's a abstract method

}
