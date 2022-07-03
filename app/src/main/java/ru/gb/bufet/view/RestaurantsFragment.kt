package ru.gb.bufet.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.gb.bufet.R
import ru.gb.bufet.databinding.FragmentRestaurantsBinding
import ru.gb.bufet.model.data.BaseFragment

class RestaurantsFragment : BaseFragment<FragmentRestaurantsBinding>(
    FragmentRestaurantsBinding::inflate) {

    private lateinit var adapter: TabsAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var tabNames: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabNames = resources.getStringArray(R.array.main_display_title_tabs)
        adapter = TabsAdapter(requireActivity())
        viewPager = binding.pager
        viewPager.adapter = adapter
        tabLayout = binding.tabLayout
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurants,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TabLayoutMediator(tabLayout,viewPager){
            tab, position -> tab.text = tabNames[position]
        }.attach()
    }

    override fun init(){

    }
}