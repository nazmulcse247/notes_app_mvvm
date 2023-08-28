package com.nazmul.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazmul.notesapp.model.Note
import com.nazmul.notesapp.repository.NoteRepository
import com.nazmul.notesapp.utils.dispatcher.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository : NoteRepository,
    private val dispatchers: DispatchersProvider
) : ViewModel(){

    suspend fun saveTask(note: Note) {
        viewModelScope.launch(dispatchers.main) {
            repository.saveTask(note)
        }
    }
}