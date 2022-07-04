package ru.gb.bufet.view

import ru.gb.bufet.databinding.FragmentProfileBinding
import ru.gb.bufet.model.data.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::inflate) {

///вот так должен выглядеть фрагмент

    override fun init(){
        //some initialization
    }
}