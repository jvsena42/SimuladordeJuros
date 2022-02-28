package com.app.simuladordejuros.main

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.simuladordejuros.R
import com.app.simuladordejuros.util.SingleLiveEvent

class MainViewModel : ViewModel() {
    private var applicationModel = ApplicationModel()

    private val _toastMessage = SingleLiveEvent<Int>()
    val toastMessage: LiveData<Int> = _toastMessage

    private val _nextScreen = SingleLiveEvent<Int>()
    val nextScreen: LiveData<Int> = _nextScreen

    fun setInitialValue(value: Editable?) {
        if (!value.isNullOrEmpty()) {
            val convertedValue = value.toString().toDouble()
            applicationModel.initialValue = convertedValue
            _nextScreen.value = R.id.action_initialValueFragment_to_applicationValueFragment
        } else {
            errorMessage()
        }
    }

    fun setApplicationValue(value: Editable?) {
        if (!value.isNullOrEmpty()) {
            val convertedValue = value.toString().toDouble()
            applicationModel.aplicationValue = convertedValue
            _nextScreen.value = R.id.action_applicationValueFragment_to_taxFragment
        } else {
            errorMessage()
        }
    }

    fun setTaxValue(value: Editable?) {
        if (!value.isNullOrEmpty()) {
            val convertedValue = value.toString().toDouble()
            applicationModel.tax = convertedValue
            _nextScreen.value = R.id.action_taxFragment_to_timeFragment
        } else {
            errorMessage()
        }
    }

    fun setTimeValue(value: Editable?) {
        if (!value.isNullOrEmpty()) {
            val convertedValue = value.toString().toDouble()
            applicationModel.tax = convertedValue
            _nextScreen.value = R.id.action_timeFragment_to_resultFragment
        } else {
            errorMessage()
        }
    }

    private fun errorMessage() {
        _toastMessage.value = R.string.mandatory_field
    }
}