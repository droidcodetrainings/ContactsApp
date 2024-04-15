package pl.farmaprom.trainings.contactsapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val LightColorsScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnSurface,
    secondary = LightSecondary,
    onSecondary = LightOnSurface,
    tertiary = LightTertiary,
    onTertiary = LightOnSurface,
    surface = LightSurface,
    onSurface = LightOnSurface,
    background = LightSurface,
    onBackground = LightOnSurface
)
private val DarkColorsScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnSurface,
    secondary = DarkSecondary,
    onSecondary = DarkOnSurface,
    tertiary = DarkTertiary,
    onTertiary = DarkOnSurface,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    background = DarkSurface,
    onBackground = DarkOnSurface
)

@Composable
fun ContactsAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = when {
        darkTheme -> DarkColorsScheme
        else -> LightColorsScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
