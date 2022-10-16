package com.example.notekmm.data

import com.example.notekmm.data.models.Note
import com.example.notekmm.data.objects.NoteObject
import com.example.notekmm.domain.NoteDataSource
import io.realm.kotlin.Realm
import io.realm.kotlin.types.ObjectId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteDataSourceImpl(private val realmDb: Realm) : NoteDataSource {
    override suspend fun insertNote(text: String) {
        realmDb.write {
            copyToRealm(NoteObject().apply {
                this.text = text
            })
        }
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return realmDb.query(NoteObject::class).asFlow()
            .map { change -> change.list.toList().map { Note(id = it.id.toString(), text = it.text) }.reversed() }
    }

    override suspend fun deleteNote(id: String) {
        realmDb.write {
            val item = query(NoteObject::class, "id == $0", ObjectId.from(id)).find().first()
            delete(item)
        }
    }
}