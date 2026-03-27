package com.myapp.dailynote.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.dailynote.data.local.FolderEntity
import com.myapp.dailynote.data.repository.FolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageViewModel @Inject constructor(
    private val repo : FolderRepository
) : ViewModel(){


    //    chip on list
    val folders = repo.getAllFolderUser()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    /* insert By Id
        */
    fun insertFolder(name: String, color: Int, icon: Int){
        viewModelScope.launch {
            repo.insertFolderUser(
                FolderEntity(
                    folderName = name,
                    colorPicker = color,
                    iconPicker = icon
                )
            )
        }
    }

    /* Delete By Id
    */
    fun deleteTodo(id: Int){
        viewModelScope.launch {
            try {
                repo.deleteTodoById(id)
                repo.deleteFolderById(id)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}