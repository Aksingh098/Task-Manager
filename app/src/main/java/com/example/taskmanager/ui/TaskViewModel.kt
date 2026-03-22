package com.example.taskmanager.ui

import androidx.lifecycle.ViewModel
import com.example.taskmanager.model.Priority
import com.example.taskmanager.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel: ViewModel() {
    private val _addTaskUiState = MutableStateFlow(AddTaskUiState())
    private val _taskUiState = MutableStateFlow(TaskUiState())

    val addTaskUiState: StateFlow<AddTaskUiState> = _addTaskUiState.asStateFlow()


    val taskUiState: StateFlow<AddTaskUiState> = _addTaskUiState.asStateFlow()


    fun addTitle(title: String){
        _addTaskUiState.update { currentState->
            currentState.copy(
                title = title
            )
        }

    }

    fun addDescription(description: String){
        _addTaskUiState.update { currentState->
            currentState.copy(
                description = description
            )
        }

    }

    fun updatePriority(priority: Priority) {
        _addTaskUiState.update { currentState ->
            currentState.copy(
                selectedpriority = priority
            )
        }
    }



    fun addTask(title: String, description: String, priority: Priority) {
        _taskUiState .update { currentState ->
            val newTask = Task(
                id = (currentState.itemList.maxOfOrNull { it.id } ?: 0) + 1,
                title = title,
                description = description,
                priority = priority
            )
            currentState.copy(itemList = currentState.itemList + newTask)
        }
    }


    fun toggleTaskCompletion(task: Task, isCompleted: Boolean) {
        _taskUiState .update { currentState ->
            val updatedList = currentState.itemList.map {
                if (it.id == task.id) it.copy(isCompleted = isCompleted) else it
            }
            currentState.copy(itemList = updatedList)
        }
    }

}