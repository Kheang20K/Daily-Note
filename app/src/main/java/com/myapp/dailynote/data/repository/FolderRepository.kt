package com.myapp.dailynote.data.repository

import com.myapp.dailynote.data.local.FolderEntity
import com.myapp.dailynote.data.local.FolderUserDao
import com.myapp.dailynote.data.local.TodoEntity
import com.myapp.dailynote.data.local.TodosDao
import javax.inject.Inject

class FolderRepository @Inject constructor(
    private val folderDao : FolderUserDao,
    private val todoDao: TodosDao
) {
    suspend fun getFolderUser(id: Int) =
        folderDao.getFolderUserById(id)
    suspend fun insertFolderUser(folder: FolderEntity) =
        folderDao.insertFolderUser(folder)

    fun getAllFolderUser() =
        folderDao.getAllFolderUser()
//    Todo
    fun getAllTodos() = todoDao.getAllTodos()
    suspend fun insertTodo(todo : TodoEntity) =
        todoDao.insertTodo(todo)

    /*
    Delete repo
     */
//    suspend fun deleteFolderUser(folder: FolderEntity) =
//        folderDao.deleteFolderUser(folder)
    suspend fun deleteFolderById (id: Int) = folderDao.deleteFolderById(id)
    suspend fun deleteTodoById(id: Int) = todoDao.deleteTodosByFolderId(id)

}