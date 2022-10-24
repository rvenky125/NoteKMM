package com.example.notekmm.domain

import com.example.notekmm.domain.models.Note
import com.example.notekmm.data.objects.NoteObject
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {
    suspend fun insertNote(text: String)
    fun getAllNotes(): Flow<List<Note>>
    suspend fun deleteNote(id: String)
}