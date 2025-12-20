package com.myapp.dailynote.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder_table")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val title: String,
    val message: String,
    val triggerTimeMillis: Long

)