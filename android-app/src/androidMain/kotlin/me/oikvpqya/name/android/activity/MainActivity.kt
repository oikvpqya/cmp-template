package me.oikvpqya.name.android.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import me.oikvpqya.name.android.application.AndroidApplication
import me.oikvpqya.name.app.application.LocalApplicationGraph
import me.oikvpqya.name.app.ui.App

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val applicationGraph = (application as AndroidApplication).applicationGraph
        setContent {
            enableEdgeToEdge()
            CompositionLocalProvider(
                LocalApplicationGraph provides applicationGraph,
            ) {
                App()
            }
        }
    }
}
