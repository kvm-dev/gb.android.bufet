package ru.gb.bufet.view

import ru.gb.bufet.MainActivity
import ru.gb.bufet.R
import ru.gb.bufet.databinding.FragmentReserveBinding
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.model.data.ReserveTables
import ru.gb.bufet.model.responseData.ReservedTables
import ru.gb.bufet.model.responseData.RestaurantTable

class ReserveFragment : BaseFragment<FragmentReserveBinding>(
    FragmentReserveBinding::inflate) {

    override fun init() {
        //test table data
        run{
            val reserved1 = ReservedTables(1, 1658672209, 0, 0, 1, 1)
            val reserved2 = ReservedTables(1, 1658758609, 1, 0, 1, 2)
            val reserved3 = ReservedTables(1, 1658845009, 1, 1, 1, 3)
            val reserved4 = ReservedTables(1, 1658931409, 1, 0, 0, 4)
            val reserved5 = ReservedTables(1, 1659017809, 0, 1, 0, 5)
            val reservedTables = listOf<ReservedTables>(reserved1, reserved2, reserved3, reserved4, reserved5)
            var testTable = RestaurantTable(1, null, 3, 8, 2, reservedTables)
            viewModel.currentTable.value = testTable

        }
        binding.calendar.apply {
            setDate(ReserveTables(requireContext(), viewModel.currentTable.value).currentDate())
            setEvents(ReserveTables(requireContext(), viewModel.currentTable.value).getReservedTables())
            setMinimumDate(ReserveTables(requireContext(), viewModel.currentTable.value).currentDate())
        }
        binding.calendar.setOnDayClickListener {
            val bottomSheet = ReserveTimeTableDialog()
            bottomSheet.show(
                requireActivity().supportFragmentManager,
                getString(R.string.dialog_select_time_reserve_table)
            )
        }
        binding.backButton.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }
}


