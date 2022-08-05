package ru.gb.bufet.view

import android.content.DialogInterface
import ru.gb.bufet.R
import ru.gb.bufet.databinding.DialogPaymentTableReserveBinding
import ru.gb.bufet.model.utils.PaymentUtils
import ru.gb.bufet.view.base.BaseBottomSheetDialog


class PaymentTableDialog : BaseBottomSheetDialog<DialogPaymentTableReserveBinding>(
    DialogPaymentTableReserveBinding::inflate) {
    private val validationCardData = arrayListOf(false, false, false)
    override fun init(){
        dialog?.setCanceledOnTouchOutside(false)
        val cardNumberSlots = "cardNumberSlots"
        val cardExpirationSlots = "cardExpirationSlots"
        val cardCvvSlots = "cardCvvSlots"
        observers()
        //validator init
        with(PaymentUtils(requireContext(), binding.dialogTablePaymentCardNumberEt)){
            this.validator(cardNumberSlots).also {
                watcher(cardNumberSlots)
            }
        }
        with(PaymentUtils(requireContext(), binding.dialogTablePaymentExpirationDateEt)){
            this.validator(cardExpirationSlots).also {
                watcher(cardExpirationSlots)
            }
        }
        with(PaymentUtils(requireContext(), binding.dialogTablePaymentCvCodeEt)){
            this.validator(cardCvvSlots).also {
                watcher(cardCvvSlots)
            }
        }
        binding.dialogTablePaymentCancelBtn.setOnClickListener {
            dismiss()
        }
        binding.dialogTablePaymentAcceptBtn.setOnClickListener {
            dismiss()
            val bottomSheet = PaymentTableVerificationDialog()
            bottomSheet.show(
                requireActivity().supportFragmentManager,
                getString(R.string.dialog_payment_reserve_table_verification)
            )
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        viewModel.paymentCardNumberValid.value = null
        viewModel.paymentCardExpirationValid.value = null
        viewModel.paymentCardCvvValid.value = null
        super.onDismiss(dialog)
    }
    private fun observers(){
        viewModel.paymentCardNumberValid.observe(viewLifecycleOwner) {
            if (it != null) {
                validationCardData[0] = it
                checkValidation()
            }
        }
        viewModel.paymentCardExpirationValid.observe(viewLifecycleOwner) {
            if (it != null) {
                validationCardData[1] = it
                checkValidation()
            }
        }
        viewModel.paymentCardCvvValid.observe(viewLifecycleOwner) {
            if (it != null) {
                validationCardData[2] = it
                checkValidation()
            }
        }
    }
    private fun checkValidation(){
        binding.dialogTablePaymentAcceptBtn.isEnabled = validationCardData == (arrayListOf(true, true, true))
    }
}