package com.example.mynots2.ui.create_notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynots2.App
import com.example.mynots2.data.model.NotesModel
import com.example.mynots2.databinding.FragmentCreateNotesBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CreateNotesFragment : Fragment() {
    private lateinit var binding: FragmentCreateNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(inflater, container, false)

        ViewCompat.setOnApplyWindowInsetsListener(binding.CreateNotesFragment) { v, insets ->
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

        val calendar: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd MMMM HH:mm", Locale.forLanguageTag("ru-RU"))
        val formattedDate = sdf.format(calendar.getTime())

        binding.tvDate.text = formattedDate

        binding.btArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvReady.setOnClickListener {
            val title: String = binding.textInputTitle.text.toString()
            val desc: String = binding.largeEditText.text.toString()
            val time: String = binding.tvDate.text.toString()
            App.db.dao().addNotes(
                NotesModel(
                    title = title,
                    desc = desc, time = time
                )
            )
            findNavController().navigateUp()
        }
    }
}


