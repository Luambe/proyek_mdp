package com.example.code.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.User
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val userRepository = ManageApp.userRepository
    private val _user = MutableLiveData<User?>()
    private val _users = MutableLiveData<List<User>>() // Perbaiki tipe LiveData menjadi List<User>
    val user: LiveData<User?>
        get() = _user

    val users: LiveData<List<User>> // Perbaiki tipe LiveData menjadi List<User>
        get() = _users

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _status = MutableLiveData<String>("idle")
    val status: LiveData<String>
        get() = _status

    fun login(username: String, password: String) {
        _status.value = "loading"
        viewModelScope.launch {
            try {
                val user = userRepository.getUserByIdAndPassword(username, password)
                println("USER:${user}")
                if (user != null) {
                    _user.postValue(user)
                    _error.postValue(null)
                    _status.postValue("success")
                } else {
                    _user.postValue(null)
                    _error.postValue("User not found or incorrect password")
                    _status.postValue("error")
                }
                println("user: ${user}")
            } catch (e: Exception) {
                _user.postValue(null)
                _error.postValue("An error occurred: ${e.message}")
                _status.postValue("error")
            }
        }
    }

    fun getAllUsers(){
        viewModelScope.launch {
            _users.postValue(userRepository.getAllUsers(true))
        }
    }


}
