package com.myapp.dailynote.data.model

data class NoteUiState(
    val id: Int,
    val title: String,
    val isDone: Boolean,
    val dueText: String
)
