package com.example.taskmanager.ui

import androidx.lifecycle.ViewModel


import com.example.taskmanager.model.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddTaskViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(AddTaskUiState())

    val uiState: StateFlow<AddTaskUiState> = _uiState.asStateFlow()


    fun addTitle(title: String){
        _uiState.update { currentState->
            currentState.copy(
                title = title
            )
        }

    }

    fun addDescription(description: String){
        _uiState.update { currentState->
            currentState.copy(
                description = description
            )
        }

    }

    fun updatePriority(priority: Priority) {
        _uiState.update { it.copy(selectedpriority = priority) }
    }

}


