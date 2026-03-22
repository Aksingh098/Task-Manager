package com.example.taskmanager.ui


import com.example.taskmanager.model.Task
import com.example.taskmanager.model.fakeTasks

data class TaskUiState(
    val itemList: List<Task> =fakeTasks


    )
