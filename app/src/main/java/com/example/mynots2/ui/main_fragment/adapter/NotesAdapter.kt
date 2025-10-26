package com.example.mynots2.ui.main_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynots2.data.model.NotesModel
import com.example.mynots2.databinding.ItemNotsBinding

class NotesAdapter(val onLongClick:(NotesModel)-> Unit,
                   val onClick:(NotesModel)-> Unit) :
    RecyclerView.Adapter<NotesAdapter.MainHolder>() {


    var notesList = arrayListOf<NotesModel>()

    fun addNotes(notes: List<NotesModel>) {
        notesList.clear()
        notesList.addAll(notes)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainHolder {


        return MainHolder(
            ItemNotsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MainHolder,
        position: Int
    ) {
        holder.onBind(notesList[position])

    }

    override fun getItemCount(): Int {

        return notesList.size
    }

    inner class MainHolder(private val binding: ItemNotsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(notesModel: NotesModel) {
            binding.tvTitle.text = notesModel.title
            binding.tvDesc.text = notesModel.desc
            binding.tvTime.text = notesModel.time

            itemView.setOnLongClickListener {
                onLongClick(notesModel)
                false
            }

            itemView.setOnClickListener {
                onClick(notesModel)
                false
            }
        }


        }

    }
