package me.oikvpqya.name.desktop.application

import me.oikvpqya.name.app.application.ApplicationGraph
import me.oikvpqya.name.app.application.ApplicationGraphImpl

class DesktopApplicationGraphImpl :
    DesktopApplicationGraph,
    ApplicationGraph by ApplicationGraphImpl()
