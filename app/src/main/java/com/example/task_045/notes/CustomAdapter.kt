package com.example.task_045

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val notes: MutableList<Note?>):
    RecyclerView.Adapter<CustomAdapter.NoteViewHolder>(){

    private var onNoteClickListener: OnNoteClickListener? = null

    interface OnNoteClickListener {
        fun onNoteClick(note: Note?, position: Int)
    }

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val noteContentTV: TextView = itemView.findViewById(R.id.noteContentTV)
        val noteDataTV: TextView = itemView.findViewById(R.id.noteDataTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val content = notes[position]?.content
        val data = notes[position]?.data

        holder.noteContentTV.text = content
        holder.noteDataTV.text = data

        holder.itemView.setOnClickListener{
            if (onNoteClickListener != null) {
                onNoteClickListener!!.onNoteClick(notes[position], position)
            }
        }
    }

    fun setOnNoteClickListener(onNoteClickListener: OnNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener
    }
}