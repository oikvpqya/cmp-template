package me.oikvpqya.runtime.ui.internal

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import me.oikvpqya.runtime.ui.EventBus

internal class EventBusImpl<EVENT> : EventBus<EVENT> {

    private val mutableEventFlow = MutableSharedFlow<EVENT>(
        extraBufferCapacity = 20,
    )

    override val events: SharedFlow<EVENT>
        get() = mutableEventFlow.asSharedFlow()

    override fun produceEvent(event: EVENT) {
        mutableEventFlow.tryEmit(event)
    }
}
