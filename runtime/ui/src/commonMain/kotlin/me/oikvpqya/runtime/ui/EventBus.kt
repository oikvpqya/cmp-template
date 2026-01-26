package me.oikvpqya.runtime.ui

import kotlinx.coroutines.flow.SharedFlow

interface EventBus<EVENT> {

    val events: SharedFlow<EVENT>

    fun produceEvent(event: EVENT)
}
