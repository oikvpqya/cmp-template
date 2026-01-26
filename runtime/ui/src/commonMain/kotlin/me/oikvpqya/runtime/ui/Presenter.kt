package me.oikvpqya.runtime.ui

import kotlinx.coroutines.flow.StateFlow

interface Presenter<EVENT, STATE> {

    val eventBus: EventBus<EVENT>

    val uiState: StateFlow<STATE>

    suspend fun handleEvent(event: EVENT)
}
