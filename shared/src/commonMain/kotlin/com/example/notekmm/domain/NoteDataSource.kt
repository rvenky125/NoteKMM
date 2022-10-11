package com.example.notekmm.domain

import com.example.notekmm.data.objects.NoteObject
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {
    suspend fun insertNote(noteObject: NoteObject)
    fun getAllNotes(): Flow<List<NoteObject>>
}