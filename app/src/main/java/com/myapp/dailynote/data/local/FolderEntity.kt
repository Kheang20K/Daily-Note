package com.myapp.dailynote.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "folder_user")
data class FolderEntity(
    @PrimaryKey(autoGenerate = true)
    val folderId : Int =0,
    val folderName: String,
    val colorPicker: Int,
    val iconPicker: Int
)

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title : String,
    val folderId: Int
)
