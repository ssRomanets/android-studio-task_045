package com.example.task_045.man

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.task_045.R
import com.example.task_045.databinding.FragmentManBinding
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/**
 * A simple [Fragment] subclass.
 * Use the [ManFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ManFragment : Fragment() {
    private var _binding: FragmentManBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentManBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.textAgeTV.setOnClickListener{ defAge() }
        binding.textFullNameTV.setOnClickListener{ defFullName() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun defAge()
    {
        val dialogBuilder = AlertDialog.Builder(activity as AppCompatActivity)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.def_age_dialog, null)
        dialogBuilder.setView(dialogView)
        val ageET= dialogView.findViewById<EditText>(R.id.defAgeET)

        dialogBuilder.setTitle("Определить возраст")
        dialogBuilder.setMessage("введите возраст:")
        dialogBuilder.setPositiveButton("Создать") { _, _ ->
            binding.textAgeTV.text = ageET.text.toString()
            Toast.makeText(context, "Возраст введен", Toast.LENGTH_LONG).show()
        }

        dialogBuilder.setNegativeButton("Отмена"){dialog, which ->}
        dialogBuilder.create().show()
    }

    fun defFullName()
    {
        val dialogBuilder = AlertDialog.Builder(activity as AppCompatActivity)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.def_fullname_dialog, null)
        dialogBuilder.setView(dialogView)
        val fullNameET= dialogView.findViewById<EditText>(R.id.defFullNameET)

        dialogBuilder.setTitle("Определить полное имя")
        dialogBuilder.setMessage("введите полное имя:")
        dialogBuilder.setPositiveButton("Создать") { _, _ ->
            binding.textFullNameTV.text = fullNameET.text.toString()
            Toast.makeText(context, "Полное имя введено", Toast.LENGTH_LONG).show()
        }

        dialogBuilder.setNegativeButton("Отмена"){dialog, which ->}
        dialogBuilder.create().show()
    }
}