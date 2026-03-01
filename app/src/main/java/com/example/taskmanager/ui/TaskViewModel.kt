package com.example.taskmanager.ui

import androidx.lifecycle.ViewModel
import com.example.taskmanager.model.Priority
import com.example.taskmanager.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())

    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    fun addTask(title: String, description: String, priority: Priority) {
        _uiState.update { currentState ->
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
        _uiState.update { currentState ->
            val updatedList = currentState.itemList.map {
                if (it.id == task.id) it.copy(isCompleted = isCompleted) else it
            }
            currentState.copy(itemList = updatedList)
        }
    }

}