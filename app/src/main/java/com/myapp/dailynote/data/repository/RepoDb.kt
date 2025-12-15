package com.myapp.dailynote.data.repository

import com.myapp.dailynote.data.database.NoteDao
import com.myapp.dailynote.data.database.NoteDataUser
import javax.inject.Inject

class RepoDb @Inject constructor(
    private val noteDao: NoteDao
) {
    suspend fun insert(note: NoteDataUser) = noteDao.insertNote(note)
    suspend fun delete(note: NoteDataUser) = noteDao.noteDelete(note)

    suspend fun getAllNote() = noteDao.getAllNotes()
}