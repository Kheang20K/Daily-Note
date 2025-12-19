package com.myapp.dailynote.data.repository

import com.myapp.dailynote.data.database.NoteDao
import com.myapp.dailynote.data.database.NoteDataUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoDb @Inject constructor(
    private val noteDao: NoteDao
) {
    fun getAllNotes() : Flow<List<NoteDataUser>> = noteDao.getAllNotes()
    suspend fun insert(note: NoteDataUser) {
        noteDao.insertNote(note)
    }
    suspend fun delete(note: NoteDataUser) {
        noteDao.noteDelete(note)
    }

}