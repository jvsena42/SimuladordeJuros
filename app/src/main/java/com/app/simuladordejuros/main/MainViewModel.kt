package com.app.simuladordejuros.main

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.simuladordejuros.R
import com.app.simuladordejuros.util.SingleLiveEvent

class MainViewModel : ViewModel() {
    private var _applicationModel = CapitalInvestment()

    private val _toastMessage = SingleLiveEvent<Int>()
    val toastMessage: LiveData<Int> = _toastMessage

    private val _nextScreen = SingleLiveEvent<Int>()
    val nextScreen: LiveData<Int> = _nextScreen

    private val _resultList = MutableLiveData<List<CapitalInvestment>>()
    val resultList: LiveData<List<CapitalInvestment>> = _resultList

    private val _amountText = MutableLiveData<String>()
    val amountText: LiveData<String> = _amountText

    fun setInitialValue(value: Editable?) {
        if (!value.isNullOrEmpty()) {
            val convertedValue = value.toString().toDouble()
            _applicationModel.initialValue = convertedValue
            _nextScreen.value = R.id.action_initialValueFragment_to_applicationValueFragment
        } else {
            errorMessage()
        }
    }

    fun setApplicationValue(value: Editable?) {
        if (!value.isNullOrEmpty()) {
            val convertedValue = value.toString().toDouble()
            _applicationModel.applicationValue = convertedValue
            _nextScreen.value = R.id.action_applicationValueFragment_to_taxFragment
        } else {
            errorMessage()
        }
    }

    fun setTaxValue(value: Editable?) {
        if (!value.isNullOrEmpty()) {
            val convertedValue = value.toString().toDouble()
            _applicationModel.tax = convertedValue
            _nextScreen.value = R.id.action_taxFragment_to_timeFragment
        } else {
            errorMessage()
        }
    }

    fun setTimeValue(value: Editable?) {
        if (!value.isNullOrEmpty()) {
            val convertedValue = value.toString().toDouble()
            _applicationModel.tax = convertedValue
            _nextScreen.value = R.id.action_timeFragment_to_resultFragment
        } else {
            errorMessage()
        }
    }

    fun calc() {
        val applicationList = mutableListOf<CapitalInvestment>()

        var i = 1
        while (i <= _applicationModel.time) {
            val currentApplication = CapitalInvestment().apply {
                month = i
                tax = _applicationModel.tax
                applicationValue = _applicationModel.applicationValue
                previousBalance = if (month == 1) _applicationModel.initialValue else balance
                balance = previousBalance * tax + applicationValue
            }
            applicationList.add(currentApplication)
            i++
        }
        _resultList.value = applicationList
        _amountText.value = String.format("%.2f", applicationList.last().balance)
    }

    private fun errorMessage() {
        _toastMessage.value = R.string.mandatory_field
    }
}