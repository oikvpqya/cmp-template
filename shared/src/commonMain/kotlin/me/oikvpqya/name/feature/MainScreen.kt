package me.oikvpqya.name.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import me.oikvpqya.name.app.application.LocalApplicationGraph
import me.oikvpqya.runtime.ui.collectAsState
import me.oikvpqya.runtime.ui.rememberEventBus

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val applicationGraph = LocalApplicationGraph.current
    val eventBus = rememberEventBus<MainUiEvent>()
    val uiState by remember(applicationGraph, eventBus) {
        MainPresenter(
            mainRepository = applicationGraph.mainRepository,
            eventBus = eventBus,
        )
    }.collectAsState()
    Column(
        modifier = modifier.safeDrawingPadding()
    ) {
        Text(text = uiState.string)
        Button(
            onClick = {
                eventBus.produceEvent(
                    event = MainUiEvent.Refresh,
                )
            },
            enabled = uiState.loadable,
        ) {
            Text(
                text = "Refresh",
            )
        }
    }
    LaunchedEffect(Unit) {
        eventBus.produceEvent(
            event = MainUiEvent.Refresh,
        )
    }
}
