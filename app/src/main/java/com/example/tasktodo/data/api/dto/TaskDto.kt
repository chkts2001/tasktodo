package com.example.tasktodo.data.api.dto

import com.google.gson.annotations.SerializedName

data class TaskDto(
    @SerializedName("id") val id: String,
    @SerializedName("head") val head: String,
    @SerializedName("description") val description: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("implementation") val important: String
)