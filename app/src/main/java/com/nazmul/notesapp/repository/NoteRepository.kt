package com.nazmul.notesapp.repository

import com.nazmul.notesapp.model.Note

interface NoteRepository {

    suspend fun saveTask(note: Note)
    //suspend fun getAllNotes(): Flow<List<Note>>
}