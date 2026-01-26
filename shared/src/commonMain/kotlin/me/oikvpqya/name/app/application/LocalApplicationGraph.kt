package me.oikvpqya.name.app.application

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

val LocalApplicationGraph: ProvidableCompositionLocal<ApplicationGraph>
        = staticCompositionLocalOf { error("CompositionLocal LocalApplicationGraph not present.") }
