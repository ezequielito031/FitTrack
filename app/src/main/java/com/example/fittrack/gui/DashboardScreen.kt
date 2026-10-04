package com.example.fittrack.gui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fittrack.viewmodel.WorkoutUiState

@Composable
fun DashboardScreen(
    uiState: WorkoutUiState,
    onCategoryClick: (String) -> Unit,
    onClearLogs: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("FitTrack Dashboard")

        Text("Total Workouts: ${uiState.totalWorkouts}")

        Text("Total Minutes: ${uiState.totalMinutes}")

        Button(
            onClick = { onCategoryClick("Strength") }
        ) {
            Text("Strength")
        }

        Button(
            onClick = { onCategoryClick("Cardio") }
        ) {
            Text("Cardio")
        }

        Button(
            onClick = { onCategoryClick("Flexibility") }
        ) {
            Text("Flexibility")
        }

        Button(
            onClick = { onCategoryClick("HIIT") }
        ) {
            Text("HIIT")
        }

        Button(
            onClick = onClearLogs
        ) {
            Text("Clear Logs")
        }
    }
}