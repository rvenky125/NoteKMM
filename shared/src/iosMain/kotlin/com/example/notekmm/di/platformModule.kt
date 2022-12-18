package com.example.notekmm.di

import com.example.notekmm.interactors.NoteViewModel
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual val platformModule = module {
    factory {
        NoteViewModel(get())
    }
}

object ViewModels : KoinComponent {
    fun noteViewModel(): NoteViewModel = get()
}