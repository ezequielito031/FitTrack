package com.example.fittrack.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fittrack.model.WorkoutLog

data class WorkoutUiState(
    val totalWorkouts: Int = 0,
    val totalMinutes: Int = 0,
    val workoutHistory: List<WorkoutLog> = emptyList()
)

class WorkoutViewModel : ViewModel() {

    var uiState by mutableStateOf(WorkoutUiState())
        private set

    private var nextWorkoutId = 1

    fun logWorkout(
        exerciseName: String,
        durationMinutes: Int
    ) {
        val newWorkout = WorkoutLog(
            id = nextWorkoutId,
            exerciseName = exerciseName,
            durationMinutes = durationMinutes
        )

        nextWorkoutId++

        uiState = uiState.copy(
            totalWorkouts = uiState.totalWorkouts + 1,
            totalMinutes = uiState.totalMinutes + durationMinutes,
            workoutHistory = listOf(newWorkout) + uiState.workoutHistory
        )
    }

    fun clearLogs() {
        uiState = WorkoutUiState()
        nextWorkoutId = 1
    }
}