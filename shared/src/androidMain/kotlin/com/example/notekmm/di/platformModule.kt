package com.example.notekmm.di

import com.example.notekmm.interactors.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual val platformModule = module {
    viewModel {
        NoteViewModel(get())
    }
}