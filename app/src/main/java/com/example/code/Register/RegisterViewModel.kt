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
    private val _users = MutableLiveData<List<User>>(null)

    val user: LiveData<User?>
        get() = _user

    val users: LiveData<List<User>>
        get() = _users

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
        println("Name: $userName")
        println("Username: $userUsername")
        println("Password: $userPassword")
        println("Confirmation Password: $userConfirmationPassword")
        println("Phone: $userPhone")
        println("Email: $userEmail")
        println("Role: $userRole")

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
                userRepository.createUser(
                    userName,
                    userUsername,
                    userPassword,
                    userConfirmationPassword,
                    userPhone,
                    userEmail,
                    userRole
                )

                _status.postValue("success")
                _error.postValue(null)
            } catch (e: Exception) {
                // Handle the error (e.g., show a toast or log the error)
                _error.postValue("Failed to create user: ${e}")
            }
        }
    }
    fun getAllUser(){
        viewModelScope.launch {
            _users.postValue(userRepository.getAllUsers(forceUpdate = true))
        }
    }
}
