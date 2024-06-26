package com.example.code.Dashboard.Profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.User
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {
    private val userRepository = ManageApp.userRepository
    private val _user = MutableLiveData<User>() // Ubah tipe data menjadi MutableLiveData<List<User>>
    val user: LiveData<User> // Ubah tipe LiveData menjadi List<User>
        get() = _user

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _status = MutableLiveData<String>("idle")
    val status: LiveData<String>
        get() = _status

    fun getUserById(userId: String) {
        viewModelScope.launch {
            _user.postValue(userRepository.getUserById(userId))
        }
    }

    fun update(userId: String, username: String, email: String, phone: String){
        viewModelScope.launch {
            _user.postValue(userRepository.updateUser(userId,username,email,phone))
            _status.postValue("success update")
        }
    }
}