package com.myapp.dailynote.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.myapp.dailynote.data.entities.NoteDataUser
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table  ORDER BY id DESC")
    fun getAllNotes(): Flow<List<NoteDataUser>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteDataUser)

    @Update
    suspend fun updateNote(note: NoteDataUser)

    @Delete
    suspend fun noteDelete(note: NoteDataUser)

}
