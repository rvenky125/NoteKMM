package com.example.notekmm.data.objects

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject

class NoteObject: RealmObject {
    var id: ObjectId = ObjectId.create()
    var text: String = ""
}