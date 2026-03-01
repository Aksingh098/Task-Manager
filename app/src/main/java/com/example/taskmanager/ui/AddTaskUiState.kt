package com.example.taskmanager.ui

import com.example.taskmanager.model.Priority

data class AddTaskUiState (
    val title: String = "",
    val description: String = "",
    val selectedpriority: Priority = Priority.LOW


)