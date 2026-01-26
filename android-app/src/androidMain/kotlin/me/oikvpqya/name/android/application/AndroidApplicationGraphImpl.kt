package me.oikvpqya.name.android.application

import android.content.Context
import me.oikvpqya.name.app.application.ApplicationGraph
import me.oikvpqya.name.app.application.ApplicationGraphImpl

class AndroidApplicationGraphImpl(
    private val application: Context,
) : AndroidApplicationGraph,
    ApplicationGraph by ApplicationGraphImpl()
