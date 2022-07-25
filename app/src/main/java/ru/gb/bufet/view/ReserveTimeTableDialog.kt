package ru.gb.bufet.view

import ru.gb.bufet.databinding.DialogTableReserveBinding
import ru.gb.bufet.model.data.BaseBottomSheetDialog

class ReserveTimeTableDialog : BaseBottomSheetDialog<DialogTableReserveBinding>(
    DialogTableReserveBinding::inflate) {

    override fun init(){
        dialog?.setCanceledOnTouchOutside(false)
        //some initialization
        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }
}