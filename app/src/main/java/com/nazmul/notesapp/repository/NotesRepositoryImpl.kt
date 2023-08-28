package com.nazmul.notesapp.repository

import com.nazmul.notesapp.db.NotesDao
import com.nazmul.notesapp.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val dao : NotesDao) : NoteRepository {
    override suspend fun saveTask(note: Note) {
        dao.saveTask(note)
    }

   /* override suspend fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }*/
}