package ru.gb.bufet.view

import android.content.DialogInterface
import ru.gb.bufet.MainActivity
import ru.gb.bufet.databinding.DialogPaymentTableVerificationBinding
import ru.gb.bufet.model.utils.PaymentUtils
import ru.gb.bufet.view.base.BaseBottomSheetDialog

class PaymentTableVerificationDialog : BaseBottomSheetDialog<DialogPaymentTableVerificationBinding>(
    DialogPaymentTableVerificationBinding::inflate) {
    override fun init() {
        dialog?.setCanceledOnTouchOutside(false)
        //validator init
        val paymentVerificationSlots = "paymentVerificationSlots"
        with(PaymentUtils(requireContext(), binding.dialogTableVerificationCodeEt)){
            this.validator(paymentVerificationSlots).also {
                watcher(paymentVerificationSlots)
            }
        }
        binding.dialogTableVerificationCancelBtn.setOnClickListener {
            dismiss()
        }
        binding.dialogTableVerificationAcceptBtn.setOnClickListener {
            dismiss()
            (activity as MainActivity).goToTableReserved()
        }
        viewModel.paymentCodeValid.observe(viewLifecycleOwner) {
            it?.also {
                binding.dialogTableVerificationAcceptBtn.isEnabled = it
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        viewModel.paymentCodeValid.value = null
        super.onDismiss(dialog)
    }
}