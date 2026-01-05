package com.myapp.dailynote.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myapp.dailynote.data.model.SubTaskUi

@Entity(tableName = "note_table")
data class NoteDataUser(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val date: String,
    val time: String,
    val isDone: Boolean =false,
    val dueDateMillis: Long? = null,
    val dueHour: Int? = null,
    val dueMinute: Int? = null,
    val reminderDateMillis: Long? = null,
    val reminderHour: Int? = null,
    val reminderMinute: Int? = null,
    val subtasks: List<SubTaskUi>? = null,
)