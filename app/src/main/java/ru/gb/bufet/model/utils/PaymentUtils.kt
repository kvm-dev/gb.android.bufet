package ru.gb.bufet.model.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import ru.gb.bufet.MainActivity
import ru.gb.bufet.R
import ru.gb.bufet.viewModel.MainViewModel
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.slots.Slot
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class PaymentUtils(private val context: Context, private val editText: EditText) {
    private val activity : MainActivity = context as MainActivity
    private val viewModel = ViewModelProvider(activity)[MainViewModel::class.java]

    private val cardNumberSlots: Array<Slot> = UnderscoreDigitSlotsParser().parseSlots("____ ____ ____ ____ ____")
    private val cardExpirationSlots: Array<Slot> = UnderscoreDigitSlotsParser().parseSlots("__/__")
    private val cardCvvSlots: Array<Slot> = UnderscoreDigitSlotsParser().parseSlots("___")
    private val paymentVerificationSlots: Array<Slot> = UnderscoreDigitSlotsParser().parseSlots("____")

     fun validator(type: String){
         var slots = ArrayList<Slot>().toTypedArray()
         when(type){
             "cardNumberSlots"->{
                 slots = cardNumberSlots
             }
             "cardExpirationSlots"->{
                 slots = cardExpirationSlots
             }
             "cardCvvSlots"->{
                 slots = cardCvvSlots
             }
             "paymentVerificationSlots"->{
                 slots = paymentVerificationSlots
             }
         }
         val watcher: FormatWatcher = MaskFormatWatcher(MaskImpl.createNonTerminated(slots))
         watcher.installOn(editText)
    }

     fun cardNumberValidation():Boolean{
         return editText.length()==24
        }

    fun cardExpirationValidation():Boolean{
        return editText.length()==5
    }

    fun cardCVVValidation():Boolean{
        return editText.length()==3
    }

    fun paymentVerificationValidation():Boolean{
        return editText.length()==4
    }
    fun watcher(type: String):Boolean{
        var result = false
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                when(type) {
                    "cardNumberSlots" -> {
                        if(cardNumberValidation()){
                            result = true.also {
                                viewModel.paymentCardNumberValid.value = it
                                editText.setTextColor(ContextCompat.getColor(context, R.color.black))
                            }
                        }
                        else{
                            result = false.also {
                                viewModel.paymentCardNumberValid.value = it
                                editText.setTextColor(ContextCompat.getColor(context, R.color.general_color))
                            }
                        }
                    }
                    "cardExpirationSlots" -> {
                        if(cardExpirationValidation()){
                            result = true.also {
                                viewModel.paymentCardExpirationValid.value = it
                                editText.setTextColor(ContextCompat.getColor(context, R.color.black))
                            }

                        }
                        else{
                            result = false.also {
                                viewModel.paymentCardExpirationValid.value = it
                                editText.setTextColor(ContextCompat.getColor(context, R.color.general_color))
                            }

                        }
                    }
                    "cardCvvSlots" -> {
                        if(cardCVVValidation()){
                            result = true.also {
                                viewModel.paymentCardCvvValid.value = it
                                editText.setTextColor(ContextCompat.getColor(context, R.color.black))
                            }
                        }
                        else{
                            result = false.also {
                                viewModel.paymentCardCvvValid.value = it
                                editText.setTextColor(ContextCompat.getColor(context, R.color.general_color))
                            }
                        }
                    }
                    "paymentVerificationSlots" -> {
                        if(paymentVerificationValidation()){
                            result = true.also {
                                viewModel.paymentCodeValid.value = it
                                editText.setTextColor(ContextCompat.getColor(context, R.color.black))
                            }
                        }
                        else{
                            result = false.also {
                                viewModel.paymentCodeValid.value = it
                                editText.setTextColor(ContextCompat.getColor(context, R.color.general_color))
                            }
                        }
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        return result
    }
    }