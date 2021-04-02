package com.example.makenotes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="note")
public class Note {
    @PrimaryKey
    public int id;
    @ColumnInfo
    public String contents;

}
