package com.example.fittrack.gui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fittrack.model.Exercise

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
            FitTrackTopBar(
                title = exercise.name,
                subtitle = exercise.category,
                showBackButton = true,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = exercise.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        DifficultyBadge(
                            difficulty = exercise.difficulty
                        )

                        Surface(
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.secondaryContainer
                        ) {

                            Text(
                                text = "${exercise.recommendedDuration} min recommended",
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 6.dp
                                ),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Text(
                text = "How to Perform",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {

                Text(
                    text = exercise.instructions,
                    modifier = Modifier.padding(18.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = "Log Your Workout",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {

                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Enter how many minutes you completed.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    OutlinedTextField(
                        value = durationText,
                        onValueChange = {
                            durationText = it
                        },
                        label = {
                            Text("Duration (Minutes)")
                        },
                        placeholder = {
                            Text("Example: 15")
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
                        singleLine = true,
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
                        Text(
                            text = "Log Completed Workout"
                        )
                    }
                }
            }
        }
    }

    if (showSuccessDialog) {

        AlertDialog(
            onDismissRequest = {
                showSuccessDialog = false
            },
            title = {
                Text(
                    text = "Workout Completed!"
                )
            },
            text = {
                Text(
                    text = "${exercise.name} has been added to your workout history."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                    }
                ) {
                    Text("Done")
                }
            }
        )
    }
}