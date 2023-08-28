package com.nazmul.notesapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.nazmul.notesapp.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NotesDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveTask(note: Note) : Long

    /*@Query("SELECT * from notes")
    suspend fun getAllNotes(): Flow<List<Note>>*/
}