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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo : FolderRepository
) : ViewModel() {

    private val _selectedFolderId = MutableStateFlow<Int?>(null)
    val selectedFolderId = _selectedFolderId

    private val _revealedItemId = MutableStateFlow<Int?>(null)
    val revealedItemId: StateFlow<Int?> = _revealedItemId

    private val _updateState = MutableStateFlow<Boolean?> (null)
    val updateState: StateFlow<Boolean?> = _updateState

    private val _selectedTimerDate = MutableStateFlow<Long?>(null)
    val selectedTimerDate: StateFlow<Long?> = _selectedTimerDate

    fun setDate(){
        _selectedTimerDate.value = null
    }
    fun formatDate(millis : Long?): String{
        if (millis == null) return "No date"
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return millis?.let {
            formatter.format(Date(it))
        } ?: "Select date"
    }


    fun selectFolder(id: Int?){
        _selectedFolderId.value = id
    }

    fun insertTodo(title: String,dueDate: Long?){
        viewModelScope.launch {
            val todoId = selectedFolderId.value
            if (todoId != null){
                repo.insertTodo(
                    TodoEntity(
                        title =title,
                        dueDate = dueDate,
                        folderId = todoId,
                    )
                )
            }
        }
    }
    // ✅ 1. Declare TODOS FIRST
    /*I use 5000 in value in todos*/
    val todos = repo.getAllTodos()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
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
        SharingStarted.WhileSubscribed(5000),
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

    /* Update with Edit text
     */
    fun updateTitle(id: Int,title: String,dueDate: Long?){
        viewModelScope.launch {
            try {
                repo.updateTitle(id,title,dueDate)
                _updateState.value = true
            }catch (e: Exception){
                _updateState.value = true
            }
        }
    }

    /* Delete By Id
     */
    fun deleteTodoAndClose(id: Int){
        viewModelScope.launch {
            try {
                repo.deleteTodoById(id)
//                _revealedItemId.value = null
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    /*Set Count Timer*/

    fun stateTimer(todo: TodoEntity){
        viewModelScope.launch {
            val update = todo.copy(
                endTime = System.currentTimeMillis() + todo.duration,
                isRunning = true
            )
            repo.updateTodo(update)
        }
    }

    fun pauseTimer(todo: TodoEntity){
        viewModelScope.launch {
            val remaining = (todo.endTime ?: 0L) - System.currentTimeMillis()
            val update = todo.copy(
                duration = remaining.coerceAtLeast(0L),
                endTime = null,
                isRunning = false
            )
            repo.updateTodo(update)

        }
    }

    fun resetTimer(todo: TodoEntity,defaultDuration: Long = 25*60*1000L){
        viewModelScope.launch {
            val update = todo.copy(
                duration = defaultDuration,
                endTime = null,
                isRunning = false
            )
            repo.updateTodo(update)

        }
    }

    // Stop timer completely (clear endTime)
    fun stopTimer(todo: TodoEntity) {
        viewModelScope.launch {
            val updated = todo.copy(
                endTime = null,
                isRunning = false
            )
            repo.updateTodo(updated)
        }
    }





}