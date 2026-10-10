package com.example.fittrack.gui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fittrack.viewmodel.WorkoutUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    uiState: WorkoutUiState,
    onCategoryClick: (String) -> Unit,
    onClearLogs: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("FitTrack")
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
                        text = "Workout Summary",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Total Workouts: ${uiState.totalWorkouts}"
                    )

                    Text(
                        text = "Total Minutes: ${uiState.totalMinutes}"
                    )
                }
            }

            Text(
                text = "Workout Categories",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    onClick = {
                        onCategoryClick("Strength")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Strength")
                }

                Button(
                    onClick = {
                        onCategoryClick("Cardio")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cardio")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    onClick = {
                        onCategoryClick("Flexibility")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Flexibility")
                }

                Button(
                    onClick = {
                        onCategoryClick("HIIT")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("HIIT")
                }
            }

            Text(
                text = "Recent Activity",
                style = MaterialTheme.typography.titleMedium
            )

            if (uiState.workoutHistory.isEmpty()) {

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "No workouts logged yet. Select a category above to get started!",
                        modifier = Modifier.padding(16.dp)
                    )
                }

            } else {

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = uiState.workoutHistory,
                        key = { it.id }
                    ) { workout ->

                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {

                                Text(
                                    text = workout.exerciseName,
                                    style = MaterialTheme.typography.titleSmall
                                )

                                Text(
                                    text = "${workout.durationMinutes} minutes"
                                )
                            }
                        }
                    }
                }
            }

            Button(
                onClick = onClearLogs,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Clear Logs")
            }
        }
    }
}