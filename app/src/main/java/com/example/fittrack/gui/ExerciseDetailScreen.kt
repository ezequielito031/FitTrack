package com.example.fittrack.gui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fittrack.model.Exercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(
    exercise: Exercise,
    onLogWorkout: (String, Int) -> Unit,
    onBackClick: () -> Unit
) {
    var durationText by remember {
        mutableStateOf("")
    }

    var showSuccessDialog by remember {
        mutableStateOf(false)
    }

    val duration = durationText.toIntOrNull()

    val isValid = duration != null && duration > 0

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(exercise.name)
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = exercise.name,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = "Difficulty: ${exercise.difficulty}"
                    )

                    Text(
                        text = "Recommended Duration: ${exercise.recommendedDuration} minutes"
                    )
                }
            }

            Text(
                text = "Instructions",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = exercise.instructions
            )

            OutlinedTextField(
                value = durationText,
                onValueChange = {
                    durationText = it
                },
                label = {
                    Text("Duration (Minutes)")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                isError = !isValid,
                supportingText = {
                    if (!isValid) {
                        Text(
                            text = "Enter a valid number greater than 0.",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {

                    if (isValid && duration != null) {

                        onLogWorkout(
                            exercise.name,
                            duration
                        )

                        durationText = ""
                        showSuccessDialog = true
                    }
                },
                enabled = isValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log Completed Workout")
            }
        }
    }

    if (showSuccessDialog) {

        AlertDialog(
            onDismissRequest = {
                showSuccessDialog = false
            },
            title = {
                Text("Workout Logged")
            },
            text = {
                Text(
                    "${exercise.name} was successfully added to your workout history."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}