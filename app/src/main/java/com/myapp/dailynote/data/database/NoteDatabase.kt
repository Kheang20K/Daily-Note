package com.myapp.dailynote.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteDataUser::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun userDao(): NoteDao
}