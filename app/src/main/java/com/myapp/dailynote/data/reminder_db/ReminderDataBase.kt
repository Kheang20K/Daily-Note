package com.myapp.dailynote.data.reminder_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ReminderEntity::class], version = 1)
abstract class ReminderDataBase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
}