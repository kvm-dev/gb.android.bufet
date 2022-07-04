package ru.gb.bufet.view

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.gb.bufet.R
import ru.gb.bufet.databinding.FragmentRestaurantsBinding
import ru.gb.bufet.model.data.BaseFragment

class RestaurantsFragment : BaseFragment<FragmentRestaurantsBinding>(
    FragmentRestaurantsBinding::inflate
) {

    private lateinit var adapter: TabsAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var tabNames: Array<String>

    override fun init() {
        tabNames = resources.getStringArray(R.array.main_display_title_tabs)
        adapter = TabsAdapter(requireActivity())
        viewPager = binding.pager
        viewPager.adapter = adapter
        tabLayout = binding.tabLayout

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

    }
}