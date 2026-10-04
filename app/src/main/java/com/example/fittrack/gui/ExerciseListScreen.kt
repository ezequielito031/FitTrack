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
fun ExerciseListScreen(
    category: String,
    onExerciseClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Category: $category")

        Button(
            onClick = { onExerciseClick(1) }
        ) {
            Text("Exercise 1")
        }

        Button(
            onClick = { onExerciseClick(2) }
        ) {
            Text("Exercise 2")
        }

        Button(
            onClick = { onExerciseClick(3) }
        ) {
            Text("Exercise 3")
        }

        Button(
            onClick = onBackClick
        ) {
            Text("Back")
        }
    }
}