package ru.gb.bufet.view.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.gb.bufet.viewModel.MainViewModel

abstract class BaseBottomSheetDialog <T : ViewBinding>(private val bindingInflater: (layoutInflater: LayoutInflater) -> T) : BottomSheetDialogFragment() {
    private var _binding: T? = null
    // ViewModel
    protected lateinit var viewModel: MainViewModel
    protected val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        (view.parent as View).setBackgroundColor(Color.TRANSPARENT)//transparent background
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun init()

}