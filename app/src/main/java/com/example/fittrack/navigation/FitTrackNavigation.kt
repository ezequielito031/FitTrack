package com.example.fittrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fittrack.gui.DashboardScreen
import com.example.fittrack.gui.ExerciseDetailScreen
import com.example.fittrack.gui.ExerciseListScreen
import com.example.fittrack.viewmodel.WorkoutViewModel

@Composable
fun FitTrackNavigation(
    viewModel: WorkoutViewModel
) {
    val navController = rememberNavController()

    val uiState = viewModel.uiState

    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable(
            route = "dashboard"
        ) {
            DashboardScreen(
                uiState = uiState,
                onCategoryClick = { category ->
                    navController.navigate(
                        "exerciseList/$category"
                    )
                },
                onClearLogs = {
                    viewModel.clearLogs()
                }
            )
        }

        composable(
            route = "exerciseList/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val category =
                backStackEntry.arguments
                    ?.getString("category")
                    ?: ""

            ExerciseListScreen(
                category = category,
                onExerciseClick = { exerciseId ->
                    navController.navigate(
                        "exerciseDetail/$exerciseId"
                    )
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "exerciseDetail/{exerciseId}",
            arguments = listOf(
                navArgument("exerciseId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val exerciseId =
                backStackEntry.arguments
                    ?.getInt("exerciseId")
                    ?: 0

            ExerciseDetailScreen(
                exerciseId = exerciseId,
                onLogWorkout = {
                    viewModel.logWorkout(
                        durationMinutes = 10
                    )
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}