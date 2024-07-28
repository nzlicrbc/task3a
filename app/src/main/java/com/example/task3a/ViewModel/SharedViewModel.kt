package com.example.task3a.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _hiddenNumber = MutableLiveData<Int>()
    val hiddenNumber: LiveData<Int> = _hiddenNumber

    fun setHiddenNumber(number: Int) {
        _hiddenNumber.value = number
    }
}