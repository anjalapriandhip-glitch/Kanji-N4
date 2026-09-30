package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = SakuraPink,
    onPrimary = IndigoNavyDark,
    primaryContainer = CrimsonDark,
    onPrimaryContainer = SakuraPinkLight,
    secondary = JapaneseCrimson,
    onSecondary = Color.White,
    secondaryContainer = IndigoCard,
    onSecondaryContainer = Color.White,
    tertiary = MatchaGreen,
    onTertiary = Color.White,
    background = IndigoNavyDark,
    onBackground = Color(0xFFF1F1F5),
    surface = IndigoNavy,
    onSurface = Color(0xFFF1F1F5),
    surfaceVariant = IndigoSurface,
    onSurfaceVariant = Color(0xFFCCCED8)
  )

private val LightColorScheme =
  lightColorScheme(
    primary = JapaneseCrimson,
    onPrimary = Color.White,
    primaryContainer = SakuraBlush,
    onPrimaryContainer = JapaneseCrimson,
    secondary = IndigoNavy,
    onSecondary = Color.White,
    secondaryContainer = SakuraPinkLight,
    onSecondaryContainer = IndigoNavyDark,
    tertiary = MatchaGreen,
    onTertiary = Color.White,
    background = WarmPorcelain,
    onBackground = CharcoalInk,
    surface = Color.White,
    onSurface = CharcoalInk,
    surfaceVariant = WarmSurface,
    onSurfaceVariant = CharcoalMuted
  )

@Composable
fun JapanStudyTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Keep Japanese authentic palette by default
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
