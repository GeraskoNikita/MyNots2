package com.example.mynots2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//import kotlinx.serialization.Serializable


@Entity(tableName ="notes_list ")
data class NotesModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? =null,
    var title: String,
    var desc: String,
    var time: String
): Serializable
