package com.example.fittrack.model

data class Exercise(
    val id: Int,
    val name: String,
    val category: String,
    val difficulty: String,
    val preview: String,
    val instructions: String,
    val recommendedDuration: Int
)