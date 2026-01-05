package com.myapp.dailynote.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myapp.dailynote.data.entities.Converters
import com.myapp.dailynote.data.entities.NoteDataUser
import com.myapp.dailynote.data.entities.ReminderEntity

@Database(entities = [NoteDataUser::class, ReminderEntity::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun userDao(): NoteDao
    abstract fun reminderDao(): ReminderDao

}