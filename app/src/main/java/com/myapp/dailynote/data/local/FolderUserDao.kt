package com.myapp.dailynote.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolderUser(folderUser: FolderEntity)

    @Update
    suspend fun updateFolderUser(folderUser: FolderEntity)

//    @Delete
//    suspend fun deleteFolderUser(folderUser: FolderEntity)

    @Query("DELETE FROM folder_user WHERE folderId = :id")
    suspend fun deleteFolderById(id: Int)

    @Query("SELECT * FROM folder_user WHERE folderId = :id ")
    fun getFolderUserById(id: Int): Flow<FolderEntity?>

    @Query("SELECT * FROM folder_user")
    fun getAllFolderUser(): Flow<List<FolderEntity>>
}

@Dao
interface TodosDao{
    @Query("SELECT * FROM todo")
    fun getAllTodos(): Flow<List<TodoEntity>>
    @Insert
    suspend fun insertTodo(todo : TodoEntity)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteTodoById(id: Int)

    @Query("DELETE FROM todo WHERE folderId = :folderId")
    suspend fun deleteTodosByFolderId(folderId: Int)

}