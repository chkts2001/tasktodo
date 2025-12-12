package com.example.tasktodo.domain.entity

data class TaskEntity(
    val id: String,
    val head: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val important: String
)