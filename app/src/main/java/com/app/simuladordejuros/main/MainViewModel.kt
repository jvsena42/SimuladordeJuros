package com.app.simuladordejuros.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.simuladordejuros.R

class MainViewModel : ViewModel() {
    private var applicationModel = ApplicationModel()

    private val _toastMessage = MutableLiveData<Int>()
    val toastMessage: LiveData<Int> = _toastMessage

    private fun errorMessage() {
        _toastMessage.value = R.string.mandatory_field
    }
}