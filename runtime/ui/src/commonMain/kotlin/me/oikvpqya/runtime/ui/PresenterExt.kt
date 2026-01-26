package me.oikvpqya.runtime.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState

@Composable
fun <EVENT, STATE> Presenter<EVENT, STATE>.collectAsState(): State<STATE> {
    LaunchedEffect(this) {
        eventBus.events.collect { event ->
            handleEvent(event)
        }
    }
    return uiState.collectAsState()
}
