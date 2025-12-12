package com.example.tasktodo.data.api.mapper

import com.example.tasktodo.data.api.dto.TaskDto
import com.example.tasktodo.domain.entity.TaskEntity

fun TaskDto.toEntity(): TaskEntity = TaskEntity(
    id = id,
    head = head,
    description = description,
    startTime = startTime,
    endTime = endTime,
    important = important

)