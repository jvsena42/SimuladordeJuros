package com.app.simuladordejuros.main

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.simuladordejuros.R

class MainViewModel : ViewModel() {
    private var applicationModel = ApplicationModel()

    private val _toastMessage = MutableLiveData<Int>()
    val toastMessage: LiveData<Int> = _toastMessage

    private val _nextScreen = MutableLiveData<Int>()
    val nextScreen: LiveData<Int> = _nextScreen

    fun setInitialValue(value: Editable?) {
        if (!value.isNullOrEmpty()) {
            val initialValue = value.toString().toDouble()
            applicationModel.aplicationValue = initialValue
            _nextScreen.value = R.id.action_initialValueFragment_to_applicationValueFragment
        } else {
            errorMessage()
        }
    }

    private fun errorMessage() {
        _toastMessage.value = R.string.mandatory_field
    }
}