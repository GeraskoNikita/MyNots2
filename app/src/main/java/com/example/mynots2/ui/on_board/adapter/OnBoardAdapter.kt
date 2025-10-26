package com.example.mynots2.ui.on_board.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynots2.data.model.OnBoardModel

import com.example.mynots2.databinding.ItemOnBoardBinding


class OnBoardAdapter(var onBoardList: ArrayList<OnBoardModel>) :
    RecyclerView.Adapter<OnBoardAdapter.OnBoardHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardHolder {
        return OnBoardHolder(
            ItemOnBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: OnBoardHolder,
        position: Int
    ) {
        holder.onBind(onBoardList[position])


    }

    override fun getItemCount(): Int {
        return onBoardList.size
    }

    inner class OnBoardHolder(private val binding: ItemOnBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun onBind(onBoardModel: OnBoardModel) {
            binding.lottieImg.setAnimation(onBoardModel.img)
            binding.tvTitle.text = onBoardModel.title
            binding.tvDesc.text = onBoardModel.desc


        }
    }
}