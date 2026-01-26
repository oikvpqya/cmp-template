package me.oikvpqya.runtime.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import me.oikvpqya.runtime.ui.internal.EventBusImpl

fun <EVENT> buildEventBus(): EventBus<EVENT> {
    return EventBusImpl()
}

@Composable
fun <EVENT> rememberEventBus(): EventBus<EVENT> {
    return remember {
        buildEventBus()
    }
}
