package ru.gb.bufet.view

import GalleryAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import ru.gb.bufet.MainActivity
import ru.gb.bufet.databinding.FragmentRestaurantBinding
import ru.gb.bufet.model.adapters.TablesAdapter
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.model.utils.ServerUtils
import ru.gb.bufet.viewModel.FoodViewModel

class RestaurantFragment :
    BaseFragment<FragmentRestaurantBinding>(FragmentRestaurantBinding::inflate) {

    override fun init() {
        binding.backButton.setOnClickListener {
            (activity?.onBackPressed())
        }
        viewModel.currentRestaurant.value.let {
            binding.gallery.adapter =
                it?.restaurantPictures?.let { pictures -> GalleryAdapter(pictures) }

            TabLayoutMediator(binding.galleryTabs, binding.gallery) { tab, position ->
                tab.text = "${(position + 1)}"
            }.attach()
            binding.tablesRecycler.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = it?.restaurantTables?.let { tables ->
                    TablesAdapter(tables)
                }
            }
            it?.restaurantTables.let { tables ->
                binding.tableCounter.text = tables?.size.toString()
            }
            if (it != null) {
                if (it.work_start != null && it.work_end != null) {
                    binding.workTimeData.text =
                        ServerUtils().checkWorkTimeFromTimeStamp(it.work_start, it.work_end)

                }
            }
            binding.description.text = it?.description
            binding.title.text = it?.name

            binding.fragmentRestMenuBtn.setOnClickListener {

//                foodModel = FoodViewModel()
//                val restId = viewModel.currentRestaurant.value!!.id ?: 0 //скорее всего здесь у тебя null, а затем 0
//                foodModel.getFoodList(restId)
//                val action =
//                    RestaurantFragmentDirections.actionNavigationRestaurantToNavigationMenu(5)
//                findNavController().navigate(action)


                //я бы предложил ничего не передавать и просто открывать нужный нам фрагмент, например так:
                (activity as MainActivity).goToFoodMenu()

            }
        }
    }
}