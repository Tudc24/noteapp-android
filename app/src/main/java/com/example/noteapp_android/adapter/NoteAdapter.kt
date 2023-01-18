package com.example.noteapp_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noteapp_android.R
import com.example.noteapp_android.model.Note
import org.w3c.dom.Text

class NoteAdapter(
    private val context: Context,
    private val onClick: (Note) -> Unit,
    private val onDelete: (Note) -> Unit,

    ) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var listNote: List<Note> = listOf()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.txt_item_title)
        private val txtDescription: TextView = itemView.findViewById(R.id.txt_item_description)
        private val btnDelete: ImageView = itemView.findViewById(R.id.btn_delete)
        private val item: ConstraintLayout = itemView.findViewById(R.id.layout_item)
        fun onBind(note: Note) {
            txtTitle.text = note.title
            txtDescription.text = note.description

            btnDelete.setOnClickListener { onDelete(note) }
            item.setOnClickListener { onClick(note) }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(listNote[position])
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setNotes(listNote: List<Note>) {
        this.listNote = listNote
        notifyDataSetChanged()
    }
}