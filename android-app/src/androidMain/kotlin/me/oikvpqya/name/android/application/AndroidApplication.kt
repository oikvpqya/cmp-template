package me.oikvpqya.name.android.application

import android.app.Application

class AndroidApplication : Application() {

    val applicationGraph: AndroidApplicationGraph by lazy(LazyThreadSafetyMode.NONE) {
        AndroidApplicationGraphImpl(this)
    }
}
