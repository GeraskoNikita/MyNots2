package com.example.mynots2.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.mynots2.data.model.NotesModel
@Dao
interface NotesDao {

    @Query("SELECT *FROM `notes_list ` ORDER BY id DESC ")
    fun getNotes(): List<NotesModel>

    @Upsert
    fun addNotes(notesModel: NotesModel)

    @Delete
    fun deleteNote(notesModel: NotesModel)

}