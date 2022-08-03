package ru.gb.bufet.view

import android.annotation.SuppressLint
import android.view.View
import ru.gb.bufet.MainActivity
import ru.gb.bufet.R
import ru.gb.bufet.databinding.FragmentReserveBinding
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.model.data.ReserveTables
import ru.gb.bufet.model.responseData.ReservedTables
import ru.gb.bufet.model.responseData.RestaurantTable

class ReserveFragment : BaseFragment<FragmentReserveBinding>(
    FragmentReserveBinding::inflate) {

    @SuppressLint("SimpleDateFormat")
    override fun init() {

        //test table data, delete this part in the future
        run{
            val reserved1 = ReservedTables(1, 1658672209, 0, 0, 1, 1)
            val reserved2 = ReservedTables(1, 1658758609, 1, 0, 1, 2)
            val reserved3 = ReservedTables(1, 1658845009, 1, 1, 1, 3)
            val reserved4 = ReservedTables(1, 1658931409, 1, 0, 0, 4)
            val reserved5 = ReservedTables(1, 1659017809, 0, 1, 0, 5)
            val reserved6 = ReservedTables(1, 1659161910, 1, 1, 1, 6)
            val reserved7 = ReservedTables(1, 1659334710, 1, 1, 0, 7)
            val reserved8 = ReservedTables(1, 1659507510, 0, 1, 1, 8)
            val reservedTables = listOf(reserved1, reserved2, reserved3, reserved4, reserved5, reserved6, reserved7, reserved8)
            var testTable = RestaurantTable(1, null, 3, 8, 2, reservedTables)
            viewModel.currentTable.value = testTable

        }
        binding.fragmentReserveCalendar.apply {
            setDate(ReserveTables(requireContext(), viewModel.currentTable.value).currentDate())
            setEvents(ReserveTables(requireContext(), viewModel.currentTable.value).getReservedTables())
            setMinimumDate(ReserveTables(requireContext(), viewModel.currentTable.value).currentDate())
        }
        binding.fragmentReserveCalendar.setOnDayClickListener { eventDay ->
            if(!eventDay.isEnabled){
                //nothing, because the day is in the past
            }
            else{
                viewModel.currentTable.value.run {
                    viewModel.reservedTableDate.value = null
                    viewModel.reservedTableTime.value = null
                    viewModel.availableTimeTable.value = ReserveTables(requireContext(), this).checkReservationTime(eventDay)
                }
                val bottomSheet = ReserveTimeTableDialog()
                bottomSheet.show(
                    requireActivity().supportFragmentManager,
                    getString(R.string.dialog_select_time_reserve_table)
                )
                viewModel.reservedTableTime.observe(viewLifecycleOwner) {
                    if (it != null) {
                        viewModel.reservedTableDate.value = eventDay.calendar
                        binding.fragmentReservePaymentBtn.isEnabled = true
                        binding.fragmentReserveWarning.visibility = View.INVISIBLE
                    }
                    else{
                        binding.fragmentReservePaymentBtn.isEnabled = false
                        binding.fragmentReserveWarning.visibility = View.VISIBLE
                    }
                }
            }
        }
        binding.backButton.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }
}


