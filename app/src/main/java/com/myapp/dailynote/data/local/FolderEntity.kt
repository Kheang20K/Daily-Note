package com.myapp.dailynote.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(tableName = "folder_user")
data class FolderEntity(
    @PrimaryKey(autoGenerate = true)
    val folderId : Int =0,
    val folderName: String,
    val colorPicker: Int,
    val iconPicker: Int
)

@Entity(
    tableName = "todo",
    foreignKeys = [
        ForeignKey(
            entity = FolderEntity::class,
            parentColumns = ["folderId"],
            childColumns = ["folderId"],
            onDelete = ForeignKey.CASCADE
        )
    ]

)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title : String,
    val folderId: Int,
    val dueDate : Long?,
    /* Set Timer Entity Db*/
    val duration: Long = 0L,
    val endTime : Long? = null,
    val isRunning: Boolean = false,
    val isCompleted: Boolean = false
//    val todoId: Int
)
