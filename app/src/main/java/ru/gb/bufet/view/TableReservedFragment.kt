package ru.gb.bufet.view

import ru.gb.bufet.MainActivity
import ru.gb.bufet.databinding.FragmentTableReservedBinding
import ru.gb.bufet.view.base.BaseFragment

class TableReservedFragment : BaseFragment<FragmentTableReservedBinding>(FragmentTableReservedBinding::inflate) {

    override fun init() {
    binding.fragmentReservedTableGoToFoodListBtn.setOnClickListener {
        (activity as MainActivity).goToFoodMenu()
    }
    }
}