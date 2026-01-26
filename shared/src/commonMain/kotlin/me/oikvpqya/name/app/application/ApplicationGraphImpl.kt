package me.oikvpqya.name.app.application

import me.oikvpqya.name.data.MainRepository

class ApplicationGraphImpl : ApplicationGraph {
    override val mainRepository = MainRepository()
}
