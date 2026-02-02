import androidx.compose.runtime.remember
import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import app.cash.turbine.test
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import me.oikvpqya.runtime.ui.EventBus
import me.oikvpqya.runtime.ui.Presenter
import me.oikvpqya.runtime.ui.buildEventBus
import me.oikvpqya.runtime.ui.collectAsState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.time.Duration.Companion.milliseconds

class PresenterTest {

    @Test
    fun handleEvent() = runTest {
        val presenter = TestPresenter()
        presenter.uiState.test {
            assertEquals(awaitItem(), TestState(loading = false))
            presenter.handleEvent(TestEvent.Load)
            assertEquals(awaitItem(), TestState(loading = true))
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun produceEvent() = runTest {
        val eventBus = buildEventBus<TestEvent>()
        moleculeFlow(RecompositionMode.Immediate) {
            remember { TestPresenter(eventBus) }.collectAsState().value
        }.test {
            assertEquals(awaitItem(), TestState(loading = false))
            assertEquals(awaitItem(), TestState(loading = true))
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun effect() = runTest {
        val presenter = TestPresenter()
        presenter.effects.test {
            presenter.handleEvent(TestEvent.Load)
            assertIs<TestEffect.Loaded>(awaitItem())
            ensureAllEventsConsumed()
        }
    }

    sealed interface TestEffect {
        data object Loaded : TestEffect
    }

    sealed interface TestEvent {
        data object Load : TestEvent
    }

    data class TestState(
        val loading: Boolean
    )

    class TestPresenter(
        override val eventBus: EventBus<TestEvent> = buildEventBus()
    ) : Presenter<TestEffect, TestEvent, TestState> {
        private val mutableEffects = Channel<TestEffect>(Channel.BUFFERED)
        override val effects: Flow<TestEffect>
            get() = mutableEffects.receiveAsFlow()

        private val mutableUiState = MutableStateFlow(TestState(loading = false))

        override val uiState: StateFlow<TestState>
            get() = mutableUiState.asStateFlow()

        override suspend fun handleEvent(event: TestEvent) {
            when(event) {
                TestEvent.Load -> {
                    delay(100.milliseconds)
                    mutableUiState.update { state ->
                        state.copy(loading = true)
                    }
                    mutableEffects.send(TestEffect.Loaded)
                }
            }
        }

        override suspend fun subscribe() {
            handleEvent(TestEvent.Load)
        }
    }
}
