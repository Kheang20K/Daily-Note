package com.myapp.dailynote.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.dailynote.data.local.FolderEntity
import com.myapp.dailynote.data.local.TodoEntity
import com.myapp.dailynote.data.repository.FolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo : FolderRepository
) : ViewModel() {

    private val _selectedFolderId = MutableStateFlow<Int?>(null)
    val selectedFolderId = _selectedFolderId

//    private val _revealedItemIds = MutableStateFlow<Set<Int>>(emptySet())
//    val revealedItemIds: StateFlow<Set<Int>> = _revealedItemIds
private val _revealedItemId = MutableStateFlow<Int?>(null)
    val revealedItemId: StateFlow<Int?> = _revealedItemId

    fun selectFolder(id: Int?){
        _selectedFolderId.value = id
    }
    fun insertTodo(title: String){
        viewModelScope.launch {
            val folderId = selectedFolderId.value
            if (folderId != null){
                repo.insertTodo(
                    TodoEntity(
                        title =title,
                        folderId = folderId
                    )
                )
            }
        }
    }
    // ✅ 1. Declare TODOS FIRST
    val todos = repo.getAllTodos()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )
    val filteredTodo = combine(
        todos,
        selectedFolderId
    ){list, folderId ->
        if (folderId == null){
            list
        }else{
            list.filter { it.folderId == folderId }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun onItemExpanded(id: Int){
        _revealedItemId.value = id
    }

    fun onItemCollapsed(id: Int){
        if (_revealedItemId.value == id){
            _revealedItemId.value = null
        }
    }
    fun closeAllItems(){
        _revealedItemId.value = null
    }


//    /* Delete By Id
//     */
//
//    fun deleteTodo(id: Int){
//        viewModelScope.launch {
//            try {
//                repo.deleteTodoById(id)
//                repo.deleteFolderById(id)
//            }catch (e: Exception){
//                e.printStackTrace()
//            }
//        }
//    }
//
////    chip on list
//    val folders = repo.getAllFolderUser()
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(),
//            emptyList()
//        )
//
//    fun insertFolder(name: String, color: Int, icon: Int){
//        viewModelScope.launch {
//            repo.insertFolderUser(
//                FolderEntity(
//                    folderName = name,
//                    colorPicker = color,
//                    iconPicker = icon
//                )
//            )
//        }
//    }
}