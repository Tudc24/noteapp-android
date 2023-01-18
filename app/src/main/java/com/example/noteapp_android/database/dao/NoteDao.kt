package com.example.noteapp_android.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp_android.model.Note


@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("select * from note_table")
    fun getAllNote():LiveData<List<Note>>

//    @Query("select * from note_table where title_col=:title")
//    fun getNoteByTitle(title:String): LiveData<List<Note>>
}