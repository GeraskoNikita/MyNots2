package com.example.mynots2.ui.on_board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.mynots2.R
import com.example.mynots2.data.local.Pref
import com.example.mynots2.data.model.OnBoardModel
import com.example.mynots2.databinding.FragmentOnBoardBinding
import com.example.mynots2.ui.on_board.adapter.OnBoardAdapter


class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding

    private lateinit var adapter: OnBoardAdapter
    private lateinit var pref: Pref
    private var listModel = ArrayList<OnBoardModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        pref = Pref(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    1 -> {
                        binding.beginButton.visibility = View.INVISIBLE
                        binding.tvSkip.visibility = View.VISIBLE

                    }

                    2 -> {
                        binding.beginButton.visibility = View.VISIBLE
                        binding.tvSkip.visibility = View.INVISIBLE

                    }
                }
            }
        })
        binding.tvSkip.setOnClickListener {
            findNavController().navigate(R.id.action_OnBoardFragment_to_MainFragment)

            pref.saveOnBoard(true)
        }
        binding.beginButton.setOnClickListener {
            findNavController().navigate(R.id.action_OnBoardFragment_to_MainFragment)
            pref.saveOnBoard(true)
        }

        initdData()
        initView()

    }

    private fun initView() {
        adapter = OnBoardAdapter(listModel)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)


    }

    private fun initdData() {
        listModel.add(
            OnBoardModel(
                title = "Удобство",
                desc = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно.",
                img = "board_design_strategy.json"
            )
        )

        listModel.add(
            OnBoardModel(
                title = "Организация",
                desc = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время.",
                img = "cutting_board.json"
            )
        )

        listModel.add(
            OnBoardModel(
                title = "Синхронизация",
                desc = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте.",
                img = "top_stocks.json"
            )
        )


    }


}
