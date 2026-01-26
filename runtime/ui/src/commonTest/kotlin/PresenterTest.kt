import androidx.compose.runtime.remember
import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import app.cash.turbine.test
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import me.oikvpqya.runtime.ui.EventBus
import me.oikvpqya.runtime.ui.Presenter
import me.oikvpqya.runtime.ui.buildEventBus
import me.oikvpqya.runtime.ui.collectAsState
import kotlin.test.Test
import kotlin.test.assertEquals

class PresenterTest {

    @Test
    fun handleEvent() = runTest {
        val presenter = TestPresenter()
        presenter.uiState.test {
            assertEquals(TestState(loading = false), awaitItem())
            presenter.handleEvent(TestEvent.Load)
            assertEquals(TestState(loading = true), awaitItem())
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun produceEvent() = runTest {
        val eventBus = buildEventBus<TestEvent>()
        moleculeFlow(RecompositionMode.Immediate) {
            remember { TestPresenter(eventBus) }.collectAsState().value
        }.test {
            assertEquals(TestState(loading = false), awaitItem())
            eventBus.produceEvent(TestEvent.Load)
            assertEquals(TestState(loading = true), awaitItem())
            ensureAllEventsConsumed()
        }
    }

    data class TestState(
        val loading: Boolean
    )

    sealed interface TestEvent {
        data object Load : TestEvent
    }

    class TestPresenter(
        override val eventBus: EventBus<TestEvent> = buildEventBus()
    ) : Presenter<TestEvent, TestState> {

        private val mutableUiState = MutableStateFlow(TestState(loading = false))

        override val uiState: StateFlow<TestState>
            get() = mutableUiState.asStateFlow()

        override suspend fun handleEvent(event: TestEvent) {
            when(event) {
                TestEvent.Load -> {
                    mutableUiState.update { state ->
                        state.copy(loading = true)
                    }
                }
            }
        }
    }
}
