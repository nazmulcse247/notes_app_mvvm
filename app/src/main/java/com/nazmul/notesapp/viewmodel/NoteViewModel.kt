package com.nazmul.notesapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazmul.notesapp.model.Note
import com.nazmul.notesapp.repository.NoteRepository
import com.nazmul.notesapp.utils.Resource
import com.nazmul.notesapp.utils.dispatcher.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository : NoteRepository,
    private val dispatchers: DispatchersProvider
) : ViewModel(){

    private val _notes = MutableLiveData<Resource<List<Note>>>()
    val notes : LiveData<Resource<List<Note>>> = _notes

    init {
        getAllNotes()
    }

    suspend fun saveTask(note: Note) {
        viewModelScope.launch(dispatchers.main) {
            repository.saveTask(note)
        }
    }

    private fun getAllNotes() {
        viewModelScope.launch(dispatchers.main) {
            _notes.postValue(Resource.Loading())
            repository.getAllNotes().collect {
                Log.d("tag", "getAllNotes"+it.size)
                _notes.value = Resource.Success(it)
            }
        }
    }
}