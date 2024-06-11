package com.example.code.ManageAttendance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Attendance
import com.example.code.data.source.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ManageAttendanceViewModel : ViewModel() {
    private val attendanceRepository = ManageApp.attendanceRepository
    private val userRepository = ManageApp.userRepository
    private val _attendance = MutableLiveData<List<Attendance>>()
    private val _user = MutableLiveData<User>()

    val attendances: LiveData<List<Attendance>>
        get() = _attendance
    val user: LiveData<User>
        get() = _user

    fun getAttendances(force: Boolean = false) {
        viewModelScope.launch {
            _attendance.postValue(attendanceRepository.getAllAttendances(true))
        }
    }

    fun getUser(userId: String) {
        viewModelScope.launch {
            val fetchedUser = userRepository.getUserById(userId)
            _user.postValue(fetchedUser!!)
        }
    }

}