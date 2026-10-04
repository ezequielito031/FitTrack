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

@Composable
fun ExerciseDetailScreen(
    exerciseId: Int,
    onLogWorkout: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Exercise Detail")

        Text("Exercise ID: $exerciseId")

        Button(
            onClick = onLogWorkout
        ) {
            Text("Test Log 10 Minute Workout")
        }

        Button(
            onClick = onBackClick
        ) {
            Text("Back")
        }
    }
}