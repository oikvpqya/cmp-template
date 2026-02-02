package me.oikvpqya.name.feature

sealed interface MainUiEffect {
    data object Loaded : MainUiEffect
}
