package com.example.taskmanager.model

data class Task (
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,

)

enum class Priority{
    LOW,
    MEDIUM,
    HIGH
}