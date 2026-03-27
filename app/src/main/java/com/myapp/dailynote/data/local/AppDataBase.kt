package com.myapp.dailynote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FolderEntity::class, TodoEntity::class], version = 3)
abstract class AppDataBase : RoomDatabase() {
    abstract fun folderUserDao() : FolderUserDao
    abstract fun todoUserDao() : TodosDao
}