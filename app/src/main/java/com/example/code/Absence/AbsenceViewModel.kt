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
    private val _status = MutableLiveData<String>()
    private val _succes = MutableLiveData<String>()

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

    val status:LiveData<String>
        get() = _status
    val succes:LiveData<String?>
        get() = _succes
    fun createAttendance(
        userId:String,
        attendanceStatus:String
    ) {
        viewModelScope.launch {
            try {
                attendanceRepository.createAttendance(userId, attendanceStatus)
                _status.postValue("success")
            } catch (e: Exception) {
                // Tangani pengecualian di sini, contohnya:
                _status.postValue("error: ${e.message}")
            }
        }
    }

    fun getAllAttendances(){
        viewModelScope.launch{
            attendanceRepository.getAllAttendances(true)
        }
    }

    fun getAttendanceByUserId(
        userId: String
    ){
        viewModelScope.launch {
            println("Debug 3")
//            _attendance.postValue(attendanceRepository.getAttendanceByUserId(userId))
            println("Debug 4")
            if(attendanceRepository.getAttendanceByUserId(userId) == null){
                _status.postValue("belum")
            }
            else{
                _status.postValue("sudah")
                _succes.postValue(attendanceRepository.getAttendanceByUserId(userId)!!.attendanceStatus.toString())
            }
        }
    }

}