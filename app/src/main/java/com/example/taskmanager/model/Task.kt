package com.example.taskmanager.model

data class Task (
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val isCompleted: Boolean = false

)

enum class Priority{
    LOW,
    MEDIUM,
    HIGH
}