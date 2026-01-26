package me.oikvpqya.name.app.application

import me.oikvpqya.name.data.MainRepository

interface ApplicationGraph {
    val mainRepository: MainRepository
}
