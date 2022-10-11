package com.example.notekmm.data

import com.example.notekmm.data.objects.NoteObject
import com.example.notekmm.domain.NoteDataSource
import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class NoteDataSourceImpl(private val realmDb: Realm): NoteDataSource {
    override suspend fun insertNote(noteObject: NoteObject) {
        realmDb.write {
            copyToRealm(noteObject)
        }
    }

    override fun getAllNotes(): Flow<List<NoteObject>> {
        return realmDb.query(NoteObject::class).asFlow().map { it.list.toList().reversed() }
    }
}