package com.example.noteapp_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp_android.R
import com.example.noteapp_android.model.Note
import com.example.noteapp_android.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        btn_add.setOnClickListener {
            val note = Note(edt_note_title.text.toString(),edt_note_description.text.toString())
            noteViewModel.insertNote(note)
            finish()
        }
    }
}