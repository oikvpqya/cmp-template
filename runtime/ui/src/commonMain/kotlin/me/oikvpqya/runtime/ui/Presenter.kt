package me.oikvpqya.runtime.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Presenter<EFFECT, EVENT, STATE> {

    val effects: Flow<EFFECT>

    val eventBus: EventBus<EVENT>

    val uiState: StateFlow<STATE>

    suspend fun handleEvent(event: EVENT)

    suspend fun subscribe() {
        // implement here, this calls from Presenter.collectAsState()
    }
}
