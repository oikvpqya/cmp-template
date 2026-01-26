import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import me.oikvpqya.name.desktop.application.DesktopApplicationGraphImpl
import me.oikvpqya.name.app.application.LocalApplicationGraph
import me.oikvpqya.name.app.ui.App

fun main() {
    val applicationGraph by lazy {
        DesktopApplicationGraphImpl()
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "title",
        ) {
            CompositionLocalProvider(
                LocalApplicationGraph provides applicationGraph,
            ) {
                App()
            }
        }
    }
}
