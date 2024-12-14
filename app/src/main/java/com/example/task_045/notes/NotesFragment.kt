package com.example.task_045.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.example.task_045.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_045.CustomAdapter
import com.example.task_045.Note
import com.example.task_045.databinding.FragmentNotesBinding

/**
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private var adapter: CustomAdapter? = null
    var notes = mutableListOf<Note?>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, saveInstanceState: Bundle?) {
        super.onViewCreated(view, saveInstanceState)

        binding.recyclerViewRV.layoutManager = LinearLayoutManager(context)
        binding.saveBTN.setOnClickListener{
            if ( binding.noteContentET.text.toString() != "")
            {
                notes.add(
                    Note(
                        binding.noteContentET.text.toString(),
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy, hh:mm:ss")).toString()
                    )
                )
                fillNotificationsRV()
                binding.noteContentET.text.clear()
            }
        }
    }

    fun noteActions(noteIndex: Int)
    {
        val dialogBuilder = AlertDialog.Builder(activity as AppCompatActivity)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.note_details_dialog, null)
        dialogBuilder.setView(dialogView)
        val noteContentET= dialogView.findViewById<EditText>(R.id.noteContentET)

        dialogBuilder.setTitle("Действия с заметкой")
        dialogBuilder.setPositiveButton("Изменить") { _, _ ->
            val newNote = Note(
                noteContentET.text.toString(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy, hh:mm:ss")).toString()
            )
            notes.add(noteIndex + 1, newNote)
            notes.removeAt(noteIndex)
            fillNotificationsRV()
        }

        dialogBuilder.setNegativeButton("Удалить"){dialog, which ->
            notes.removeAt(noteIndex)
            fillNotificationsRV()
        }
        dialogBuilder.create().show()
    }

    fun fillNotificationsRV() {
        adapter = CustomAdapter(notes)
        binding.recyclerViewRV.adapter = adapter
        binding.recyclerViewRV.setHasFixedSize(true)
        adapter?.setOnNoteClickListener( object :
            CustomAdapter.OnNoteClickListener {
            override fun onNoteClick(notification: Note?, position: Int) {
                noteActions(position)
            }
        })
    }
}