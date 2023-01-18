package com.example.noteapp_android.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp_android.R
import com.example.noteapp_android.adapter.NoteAdapter
import com.example.noteapp_android.model.Note
import com.example.noteapp_android.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initControls()
        initEvents()

    }

    private fun initEvents() {
        btn_add_open.setOnClickListener {
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControls() {
        val adapter: NoteAdapter = NoteAdapter(this@MainActivity,onItemClick,onItemDelete)

        rv_note.setHasFixedSize(true)
        rv_note.layoutManager=LinearLayoutManager(this)
        rv_note.adapter=adapter

        noteViewModel.getAllNote().observe(this, Observer {
            adapter.setNotes(it)
        })
    }

    private val onItemClick: (Note) -> Unit = {
        val intent =Intent(this,UpdateNoteActivity::class.java)
        intent.putExtra("Update_note",it)
        startActivity(intent)

    }

    private val onItemDelete: (Note) -> Unit = {
        noteViewModel.deleteNote(it)
    }
}