package com.example.fittrack.gui
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fittrack.model.Exercise

@Composable
fun ExerciseListScreen(
    category: String,
    exercises: List<Exercise>,
    onExerciseClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {

    Scaffold(
        topBar = {
            FitTrackTopBar(
                title = category,
                subtitle = "${exercises.size} exercises",
                showBackButton = true,
                onBackClick = onBackClick
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
                    fontWeight = FontWeight.Bold,
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

    val exerciseIcon =
        categoryIcon(
            category = exercise.category
        )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.Top
        ) {

            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = categoryContainerColor(
                    category = exercise.category
                )
            ) {

                Box(
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = exerciseIcon,
                        contentDescription = exercise.category,
                        tint = categoryIconColor(
                            category = exercise.category
                        )
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(9.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
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
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Recommended: ${exercise.recommendedDuration} min",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
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
        shape = RoundedCornerShape(50),
        color = containerColor
    ) {

        Text(
            text = difficulty,
            modifier = Modifier.padding(
                horizontal = 9.dp,
                vertical = 5.dp
            ),
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}

fun categoryIcon(
    category: String
): ImageVector {

    return when (category) {

        "Strength" ->
            Icons.Default.FitnessCenter

        "Cardio" ->
            Icons.Default.DirectionsRun

        "Flexibility" ->
            Icons.Default.SelfImprovement

        else ->
            Icons.Default.Bolt
    }
}

fun categoryContainerColor(
    category: String
): Color {

    return when (category) {

        "Strength" ->
            Color(0xFFDCE6FF)

        "Cardio" ->
            Color(0xFFFFE0DC)

        "Flexibility" ->
            Color(0xFFD9F2E5)

        else ->
            Color(0xFFEADFFF)
    }
}

fun categoryIconColor(
    category: String
): Color {

    return when (category) {

        "Strength" ->
            Color(0xFF2F6BFF)

        "Cardio" ->
            Color(0xFFCC3D3D)

        "Flexibility" ->
            Color(0xFF268A5E)

        else ->
            Color(0xFF7653B5)
    }
}