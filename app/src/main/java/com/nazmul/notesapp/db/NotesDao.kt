package com.nazmul.notesapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.nazmul.notesapp.model.Note
import kotlinx.coroutines.flow.Flow



@Dao
interface NotesDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveTask(note: Note)

    @Query("SELECT * from notes")
    fun getAllNotes(): Flow<List<Note>>
}