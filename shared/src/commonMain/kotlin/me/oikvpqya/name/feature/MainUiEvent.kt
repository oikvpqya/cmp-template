package me.oikvpqya.name.feature

sealed interface MainUiEvent {
    data object Refresh : MainUiEvent
}
