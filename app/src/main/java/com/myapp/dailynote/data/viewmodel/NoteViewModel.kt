package com.myapp.dailynote.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.dailynote.data.entities.NoteDataUser
import com.myapp.dailynote.data.model.NoteUiState
import com.myapp.dailynote.data.repository.RepoDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository : RepoDb
): ViewModel(){
    private val _notes = MutableStateFlow<List<NoteDataUser>>(emptyList())
    val notes: StateFlow<List<NoteDataUser>> = _notes

    init {
        viewModelScope.launch {
            repository.getAllNotes().collect { notes ->
                _notes.value = notes
            }
        }
    }
    fun insertNote(note: NoteDataUser){
        viewModelScope.launch {
            repository.insert(note)
        }
    }
    fun deleteNote(note: NoteDataUser){
        viewModelScope.launch {
            repository.delete(note)
        }
    }
    fun upDateNote(note: NoteDataUser){
        viewModelScope.launch {
            if(note.id ==0){
                repository.upDateNote(note)
            }else{
                repository.upDateNote(note)
            }
        }
    }
    fun getDueDateText(dueMillis: Long): String {
        val now = System.currentTimeMillis()

        val startOfToday = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        val startOfDue = Calendar.getInstance().apply {
            timeInMillis = dueMillis
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        return when {
            startOfDue == startOfToday -> "Today"
            else -> SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date(dueMillis))
        }
    }
    fun getDayCountText(dueMillis: Long): Int {
        val now = System.currentTimeMillis()

        val startOfToday = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        val startOfDue = Calendar.getInstance().apply {
            timeInMillis = dueMillis
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        val diffDays =  ((startOfDue - startOfToday) / (24 * 60 * 60 * 1000)).toInt()
        return if (diffDays <0)0 else diffDays
    }


}