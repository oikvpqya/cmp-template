import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import me.oikvpqya.runtime.ui.buildEventBus
import kotlin.test.Test
import kotlin.test.assertEquals

class EventBusTest {

    @Test
    fun produceEvent() = runTest {
        val eventBus = buildEventBus<TestEvent>()
        eventBus.events.test {
            eventBus.produceEvent(TestEvent.Load)
            assertEquals(awaitItem(), TestEvent.Load)
            ensureAllEventsConsumed()
        }
    }

    sealed interface TestEvent {
        data object Load : TestEvent
    }
}
