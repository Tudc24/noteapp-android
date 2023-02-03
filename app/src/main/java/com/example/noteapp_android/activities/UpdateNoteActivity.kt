package com.example.noteapp_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp_android.R
import com.example.noteapp_android.databinding.ActivityAddNoteBinding
import com.example.noteapp_android.databinding.ActivityUpdateNoteBinding
import com.example.noteapp_android.model.Note
import com.example.noteapp_android.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateNoteActivity : AppCompatActivity() {
    private val TAG = "NOTE_VIEW_MODEL"

    private lateinit var binding: ActivityUpdateNoteBinding

    private val noteViewModel: NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "UpdateNoteActivity: ${noteViewModel.noteRepository} , $noteViewModel")

        val note = intent.getSerializableExtra("UPDATE_NOTE") as Note
        binding.edtNoteTitle.setText(note.title)
        binding.edtNoteDescription.setText(note.description)

        binding.btnUpdate.setOnClickListener {
            note.title = binding.edtNoteTitle.text.toString()
            note.description = binding.edtNoteDescription.text.toString()
            noteViewModel.updateNote(note)
            finish()
        }
    }
}