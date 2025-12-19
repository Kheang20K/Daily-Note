package com.myapp.dailynote.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapp.dailynote.data.entities.NoteDataUser
import com.myapp.dailynote.data.entities.ReminderEntity

@Database(entities = [NoteDataUser::class, ReminderEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun userDao(): NoteDao
    abstract fun reminderDao(): ReminderDao

}