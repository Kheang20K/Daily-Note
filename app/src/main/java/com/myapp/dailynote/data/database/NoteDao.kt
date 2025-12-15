package com.myapp.dailynote.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table  ORDER BY id DESC")
    suspend fun getAllNotes(): List<NoteDataUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteDataUser)

    @Update
    suspend fun updateNote(note: NoteDataUser)

    @Delete
    suspend fun noteDelete(note: NoteDataUser)

}