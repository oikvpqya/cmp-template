package me.oikvpqya.name.feature

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import me.oikvpqya.name.data.MainRepository
import me.oikvpqya.runtime.ui.EventBus
import me.oikvpqya.runtime.ui.Presenter
import me.oikvpqya.runtime.ui.buildEventBus
import kotlin.time.Duration.Companion.seconds

class MainPresenter(
    private val mainRepository: MainRepository,
    override val eventBus: EventBus<MainUiEvent> = buildEventBus(),
) : Presenter<MainUiEffect, MainUiEvent, MainUiState> {
    private val mutableEffects = Channel<MainUiEffect>(Channel.BUFFERED)
    override val effects: Flow<MainUiEffect>
        get() = mutableEffects.receiveAsFlow()

    private val mutableUiState = MutableStateFlow(
        value = MainUiState(
            string = "nothing",
            loadable = true,
        )
    )
    override val uiState: StateFlow<MainUiState>
        get() = mutableUiState.asStateFlow()

    override suspend fun handleEvent(event: MainUiEvent) {
        when (event) {
            MainUiEvent.Refresh -> {
                mutableUiState.update { uiState ->
                    uiState.copy(
                        string = "loading...",
                        loadable = false,
                    )
                }
                delay(duration = 1.seconds)
                mutableUiState.update { uiState ->
                    uiState.copy(
                        string = mainRepository.string,
                        loadable = true,
                    )
                }
                mutableEffects.send(MainUiEffect.Loaded)
            }
        }
    }

    override suspend fun subscribe() {
        handleEvent(MainUiEvent.Refresh)
    }
}
