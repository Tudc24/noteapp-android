package com.example.noteapp_android.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp_android.adapter.NoteAdapter
import com.example.noteapp_android.databinding.ActivityMainBinding
import com.example.noteapp_android.model.Note
import com.example.noteapp_android.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "NOTE_VIEW_MODEL"
    private lateinit var binding: ActivityMainBinding
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "MainActivity: ${noteViewModel.noteRepository} , $noteViewModel")

        initControls()
        initEvents()

    }

    private fun initEvents() {
        binding.btnAddOpen.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControls() {
        val adapter: NoteAdapter = NoteAdapter(this@MainActivity, onItemClick, onItemDelete)
        binding.rvNote.setHasFixedSize(true)
        binding.rvNote.layoutManager = LinearLayoutManager(this)
        binding.rvNote.adapter = adapter

        noteViewModel.getAllNote().observe(this, Observer {
            adapter.setNotes(it)
        })
    }

    private val onItemClick: (Note) -> Unit = {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE_NOTE", it)
        startActivity(intent)

    }

    private val onItemDelete: (Note) -> Unit = {
        noteViewModel.deleteNote(it)
    }
}