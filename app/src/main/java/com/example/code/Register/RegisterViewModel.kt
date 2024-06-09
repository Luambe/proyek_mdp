package com.example.code.Register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel : ViewModel() {
    private val userRepository = ManageApp.userRepository
    private val _user = MutableLiveData<User?>(null)
    val user: LiveData<User?>
        get() = _user

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _status:MutableLiveData<String> = MutableLiveData<String>("idle")
    val status:LiveData<String>
        get() = _status
    fun register(
        userName: String,
        userUsername: String,
        userPassword: String?,
        userConfirmationPassword: String,
        userPhone: String,
        userEmail: String,
        userRole: String
    ) {
        // Validate input before proceeding
        if (userPassword != userConfirmationPassword) {
            _error.value = "Passwords do not match"
            return
        }

        val newUser = User(
            userId = "",
            userName = userName,
            userUsername = userUsername,
            userPassword = userPassword,
            userPhone = userPhone,
            userEmail = userEmail,
            userRole = userRole
        )
        _status.value = "processing"
        viewModelScope.launch {
            try {
                userRepository.createUser(newUser)

                _status.postValue("success")
                _error.postValue(null)
            } catch (e: Exception) {
                // Handle the error (e.g., show a toast or log the error)
                _error.postValue("Failed to create user: ${e}")
            }
        }
    }
}
