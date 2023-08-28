package com.nazmul.notesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nazmul.notesapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase(){
    abstract fun notesDao(): NotesDao
}