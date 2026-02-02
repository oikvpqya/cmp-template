package me.oikvpqya.runtime.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState

@Composable
fun <EFFECT, EVENT, STATE> Presenter<EFFECT, EVENT, STATE>.collectAsState(): State<STATE> {
    LaunchedEffect(this) {
        subscribe()
    }
    LaunchedEffect(this) {
        eventBus.events.collect { event ->
            handleEvent(event)
        }
    }
    return uiState.collectAsState()
}

@Composable
fun <EFFECT, EVENT, STATE> Presenter<EFFECT, EVENT, STATE>.Effect(
    onEffect: suspend (EFFECT) -> Unit,
) {
    LaunchedEffect(this) {
        effects.collect { effect ->
            onEffect(effect)
        }
    }
}
