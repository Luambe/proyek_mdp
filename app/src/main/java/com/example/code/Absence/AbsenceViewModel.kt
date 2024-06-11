package com.example.code.Absence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Attendance
import com.example.code.data.source.model.Company
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AbsenceViewModel : ViewModel() {

    private val attendanceRepository = ManageApp.attendanceRepository

    private val _currentTime = MutableLiveData<String>()
    val currentTime: LiveData<String> get() = _currentTime
    private val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    init {
        startUpdatingTime()
    }

    private fun startUpdatingTime() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                val now = LocalTime.now().format(formatter)
                _currentTime.postValue(now)
                delay(1000) // Delay 1 detik
            }
        }
    }

}