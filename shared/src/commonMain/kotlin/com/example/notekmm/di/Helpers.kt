package com.example.notekmm.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin


fun getAllModules() = listOf(mainModule, platformModule)