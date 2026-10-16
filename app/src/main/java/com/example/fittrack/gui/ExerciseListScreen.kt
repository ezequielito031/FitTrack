package com.example.fittrack.gui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
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
                    Column {
                        Text(
                            text = category,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "${exercises.size} exercises",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
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
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            item {
                Text(
                    text = "Choose an Exercise",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(
                        top = 8.dp,
                        bottom = 4.dp
                    )
                )
            }

            items(
                items = exercises,
                key = { it.id }
            ) { exercise ->

                ExerciseCard(
                    exercise = exercise,
                    onClick = {
                        onExerciseClick(exercise.id)
                    }
                )
            }
        }
    }
}

@Composable
fun ExerciseCard(
    exercise: Exercise,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                DifficultyBadge(
                    difficulty = exercise.difficulty
                )
            }

            Text(
                text = exercise.preview,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Recommended: ${exercise.recommendedDuration} min",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
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
        shape = RoundedCornerShape(50),
        color = containerColor
    ) {

        Text(
            text = difficulty,
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 5.dp
            ),
            style = MaterialTheme.typography.labelMedium,
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}