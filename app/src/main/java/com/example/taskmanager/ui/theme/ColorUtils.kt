package com.example.taskmanager.ui.theme


import androidx.compose.ui.graphics.Color
import com.example.taskmanager.model.Priority



fun getPriorityColor(priority: Priority): Color {
    return when (priority) {
        Priority.HIGH -> Color(0xFFFF5252)
        Priority.MEDIUM -> Color(0xFFFFAB40)
        Priority.LOW -> Color(0xFF4CAF50)
    }
}