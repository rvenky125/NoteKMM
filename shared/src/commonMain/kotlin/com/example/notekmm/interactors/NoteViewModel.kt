package com.example.notekmm.interactors

import com.example.notekmm.data.models.Note
import com.example.notekmm.data.objects.NoteObject
import com.example.notekmm.domain.NoteDataSource
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dataSource: NoteDataSource
): ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    private val _textFieldValue = MutableStateFlow("")
    val textFieldValue: StateFlow<String> = _textFieldValue

    fun addNote(text: String) {
        viewModelScope.launch {
            dataSource.insertNote(text = text)
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
}