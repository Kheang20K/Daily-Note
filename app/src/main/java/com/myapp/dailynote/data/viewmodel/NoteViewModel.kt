package com.myapp.dailynote.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import com.myapp.dailynote.data.database.NoteDataUser
import com.myapp.dailynote.data.repository.RepoDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository : RepoDb
): ViewModel(){

    private val _notes = MutableStateFlow<List<NoteDataUser>>(emptyList())
    val notes: StateFlow<List<NoteDataUser>> = _notes

    init {
        viewModelScope.launch {
            _notes.value = repository.getAllNote()
        }
    }
    fun insertNote(note: NoteDataUser){
        viewModelScope.launch {
            repository.insert(note)
            _notes.value = repository.getAllNote()
        }
    }

}