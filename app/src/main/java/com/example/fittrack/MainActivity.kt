package com.example.fittrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fittrack.navigation.FitTrackNavigation
import com.example.fittrack.ui.theme.FitTrackTheme
import com.example.fittrack.viewmodel.WorkoutViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            FitTrackTheme {

                val workoutViewModel: WorkoutViewModel = viewModel()

                FitTrackNavigation(
                    viewModel = workoutViewModel
                )
            }
        }
    }
}