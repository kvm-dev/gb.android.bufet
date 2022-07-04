package ru.gb.bufet.view

import ru.gb.bufet.databinding.FragmentRestaurantsBinding
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.model.domain.FoodRepository
import ru.gb.bufet.retrofit.RetrofitFoodRepoImpl

class RestaurantsFragment : BaseFragment<FragmentRestaurantsBinding>(
    FragmentRestaurantsBinding::inflate
) {

    private val repo: FoodRepository = RetrofitFoodRepoImpl()

    override fun init() {
        Thread {
            val tmpDat = repo.getAllFood()

            requireActivity().runOnUiThread {
                binding.testFoodTw.text = tmpDat.toString()
            }
        }.start()
    }
}