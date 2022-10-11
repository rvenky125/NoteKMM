package com.example.notekmm.di

import com.example.notekmm.interactors.NoteViewModel
import org.koin.dsl.module

actual val platformModule = module {
    factory {
        NoteViewModel(get())
    }
}