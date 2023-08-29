package com.nazmul.notesapp.repository

import com.nazmul.notesapp.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun saveTask(note: Note)
    fun getAllNotes(): Flow<List<Note>>
}