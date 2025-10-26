package com.example.mynots2.ui.main_fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynots2.App
import com.example.mynots2.R
import com.example.mynots2.data.model.NotesModel
import com.example.mynots2.databinding.FragmentMainBinding
import com.example.mynots2.ui.main_fragment.adapter.NotesAdapter
import com.google.android.material.button.MaterialButton

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var notesList: List<NotesModel>
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        ViewCompat.setOnApplyWindowInsetsListener(binding.MainFragment) { v, insets ->
            // Получаем отступы статус-бара
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Применяем верхний отступ как padding для корневого view
            v.setPadding(
                systemBarsInsets.left,
                systemBarsInsets.top,
                systemBarsInsets.right,
                systemBarsInsets.bottom
            )

            // Возвращаем CONSUMED, чтобы отступы не передавались дочерним View
            WindowInsetsCompat.CONSUMED
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getData()
        binding.btAdd.setOnClickListener {
            findNavController().navigate(R.id.action_MainFragment_to_createNotesFragment)
        }
    }

    private fun initView() {

        adapter = NotesAdapter(::onLongClick)
        binding.rvNots.adapter = adapter
    }

    private fun getData() {
        notesList = App.db.dao().getNotes()
        adapter.addNotes(notesList)

    }

    private fun onLongClick(notesModel: NotesModel) {
        val dialogView = layoutInflater.inflate(R.layout.delete_dialog, null)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()
        val btnDelete = dialogView.findViewById<MaterialButton>(R.id.btn_delete)
        val btnCancel = dialogView.findViewById<MaterialButton>(R.id.btn_cancel)
        btnDelete.setOnClickListener {
            App.db.dao().deleteNote(notesModel)
            getData()
            dialog.dismiss()

        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }
}




