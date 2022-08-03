package ru.gb.bufet.view

import android.widget.RadioButton
import ru.gb.bufet.MainActivity
import ru.gb.bufet.R
import ru.gb.bufet.databinding.DialogTableReserveBinding
import ru.gb.bufet.model.data.BaseBottomSheetDialog
import ru.gb.bufet.model.data.ReserveTables

class ReserveTimeTableDialog : BaseBottomSheetDialog<DialogTableReserveBinding>(
    DialogTableReserveBinding::inflate) {

    override fun init(){
        dialog?.setCanceledOnTouchOutside(false)
        viewModel.availableTimeTable.value.let { 
            val info = ReserveTables(requireContext(), viewModel.currentTable.value).availabilityTimeDialog(it)
            binding.dialogReserveBreakfastBtn.isEnabled = info[0]
            binding.dialogReserveLunchBtn.isEnabled = info[1]
            binding.dialogReserveDinnerBtn.isEnabled = info[2]
            if(info == listOf(false, false, false)){
                (activity as MainActivity).toaster(resources.getString(R.string.table_today_unavailable))
            }
        }
        binding.dialogReserveCancelBtn.setOnClickListener {
            viewModel.reservedTableTime.value = null
            viewModel.reservedTableDate.value = null
            dismiss()
        }
        binding.dialogReserveTimeRG.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId != -1){
                binding.dialogReserveAcceptBtn.isEnabled = true
                binding.dialogReserveAcceptBtn.setOnClickListener {
                    for (i in 0 until binding.dialogReserveTimeRG.childCount){
                        val radioButton = binding.dialogReserveTimeRG.getChildAt(i) as RadioButton
                        if(radioButton.isChecked){
                            viewModel.reservedTableTime.value = radioButton.text.toString()
                        }
                    }
                    dismiss()
                }
            }
        }
    }
}