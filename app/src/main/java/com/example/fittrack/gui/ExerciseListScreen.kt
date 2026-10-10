package com.example.fittrack.gui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fittrack.model.Exercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListScreen(
    category: String,
    exercises: List<Exercise>,
    onExerciseClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(category)
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(
                items = exercises,
                key = { it.id }
            ) { exercise ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onExerciseClick(exercise.id)
                        }
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Text(
                            text = exercise.name,
                            style = MaterialTheme.typography.titleMedium
                        )

                        DifficultyBadge(
                            difficulty = exercise.difficulty
                        )

                        Text(
                            text = exercise.preview,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DifficultyBadge(
    difficulty: String
) {
    val containerColor =
        when (difficulty) {

            "Beginner" ->
                MaterialTheme.colorScheme.primaryContainer

            "Intermediate" ->
                MaterialTheme.colorScheme.secondaryContainer

            else ->
                MaterialTheme.colorScheme.errorContainer
        }

    val textColor =
        when (difficulty) {

            "Beginner" ->
                MaterialTheme.colorScheme.onPrimaryContainer

            "Intermediate" ->
                MaterialTheme.colorScheme.onSecondaryContainer

            else ->
                MaterialTheme.colorScheme.onErrorContainer
        }

    Surface(
        color = containerColor,
        contentColor = textColor,
        shape = RoundedCornerShape(50)
    ) {

        Text(
            text = difficulty,
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 6.dp
            ),
            style = MaterialTheme.typography.labelMedium
        )
    }
}
