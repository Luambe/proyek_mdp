package com.example.code.Dashboard.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.code.ManageApp
import com.example.code.data.source.model.User

class UserHomeViewModel : ViewModel() {
    private val userRepository = ManageApp.userRepository
    private val _user = MutableLiveData<List<User>>() // Ubah tipe data menjadi MutableLiveData<List<User>>
    val user: LiveData<List<User>> // Ubah tipe LiveData menjadi List<User>
        get() = _user

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _status = MutableLiveData<String>("idle")
    val status: LiveData<String>
        get() = _status

    fun getUserById(userId: String) {
//        val user = userRepository.getUserById(userId)
//        _user.postValue(listOf(user)) // Ubah ke List<User>
    }
}
