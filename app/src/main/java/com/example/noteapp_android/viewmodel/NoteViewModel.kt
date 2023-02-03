package com.example.noteapp_android.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noteapp_android.database.repository.NoteRepository
import com.example.noteapp_android.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.nio.channels.IllegalChannelGroupException
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val noteRepository: NoteRepository) : ViewModel() {

    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun getAllNote(): LiveData<List<Note>> = noteRepository.getAllNote()

}