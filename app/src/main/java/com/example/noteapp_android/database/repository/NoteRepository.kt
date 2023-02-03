package com.example.noteapp_android.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.noteapp_android.database.NoteDatabase
import com.example.noteapp_android.database.dao.NoteDao
import com.example.noteapp_android.model.Note
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    fun getAllNote(): LiveData<List<Note>> = noteDao.getAllNote()


}