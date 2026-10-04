package com.example.fittrack.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class WorkoutUiState(
    val totalWorkouts: Int = 0,
    val totalMinutes: Int = 0
)

class WorkoutViewModel : ViewModel() {

    var uiState by mutableStateOf(WorkoutUiState())
        private set

    fun logWorkout(durationMinutes: Int) {

        uiState = uiState.copy(
            totalWorkouts = uiState.totalWorkouts + 1,
            totalMinutes = uiState.totalMinutes + durationMinutes
        )
    }

    fun clearLogs() {
        uiState = WorkoutUiState()
    }
}