package com.example.noteapp_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels

import com.example.noteapp_android.databinding.ActivityAddNoteBinding

import com.example.noteapp_android.model.Note
import com.example.noteapp_android.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {
    private val TAG = "NOTE_VIEW_MODEL"
    private lateinit var binding: ActivityAddNoteBinding

    private val noteViewModel: NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "AddNoteActivity: ${noteViewModel.noteRepository} , $noteViewModel")

        binding.btnAdd.setOnClickListener {
            val note = Note(binding.edtNoteTitle.text.toString(),binding.edtNoteDescription.text.toString())
            noteViewModel.insertNote(note)
            finish()
        }
    }
}