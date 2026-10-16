package com.example.fittrack.ui.theme
import androidx.compose.ui.graphics.Color
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = FitBlue,
    onPrimary = FitSurface,

    primaryContainer = Color(0xFFDCE6FF),
    onPrimaryContainer = FitBlueDark,

    secondary = FitGreen,
    onSecondary = FitSurface,

    secondaryContainer = FitGreenLight,
    onSecondaryContainer = Color(0xFF14532D),

    tertiary = FitOrange,

    error = FitRed,

    background = FitBackground,
    onBackground = FitTextPrimary,

    surface = FitSurface,
    onSurface = FitTextPrimary,

    surfaceVariant = Color(0xFFE9EDF5),
    onSurfaceVariant = FitTextSecondary
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFAFC6FF),
    onPrimary = Color(0xFF002C73),

    primaryContainer = Color(0xFF1746B3),
    onPrimaryContainer = Color(0xFFDCE6FF),

    secondary = Color(0xFF8BD4AD),
    onSecondary = Color(0xFF003920),

    secondaryContainer = Color(0xFF14532D),
    onSecondaryContainer = Color(0xFFD7F2E5),

    tertiary = Color(0xFFFFC66A),

    error = Color(0xFFFFB4AB),

    background = FitDarkBackground,
    onBackground = Color(0xFFE4E5EA),

    surface = FitDarkSurface,
    onSurface = Color(0xFFE4E5EA),

    surfaceVariant = Color(0xFF2A2D35),
    onSurfaceVariant = Color(0xFFC5C7D0)
)

@Composable
fun FitTrackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val context = LocalContext.current

            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        } else {
            if (darkTheme) {
                DarkColorScheme
            } else {
                LightColorScheme
            }
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}