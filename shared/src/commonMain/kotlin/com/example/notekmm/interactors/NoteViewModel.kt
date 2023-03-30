package com.example.notekmm.interactors

import com.example.notekmm.domain.models.Note
import com.example.notekmm.domain.NoteDataSource
import dev.icerock.moko.mvvm.flow.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dataSource: NoteDataSource
): ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: CStateFlow<List<Note>> = _notes.cStateFlow()

    private val _textFieldValue = MutableStateFlow("")
    val textFieldValue: CStateFlow<String> = _textFieldValue.cStateFlow()

    fun addNote() {
        viewModelScope.launch {
            dataSource.insertNote(text = textFieldValue.value)
            _textFieldValue.value = ""
        }
    }

    fun onValueChange(text: String) {
        _textFieldValue.value = text
    }

    fun onDelete(id: String) {
        viewModelScope.launch {
            dataSource.deleteNote(id)
        }
    }

    init {
        dataSource.getAllNotes().onEach {
            _notes.value = it
        }.launchIn(viewModelScope)
    }

    fun observeNotes(onChange: (List<Note>) -> Unit) {
        notes.onEach {
            onChange(it)
        }.launchIn(viewModelScope)
    }
}
