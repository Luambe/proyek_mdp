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

//    fun getUserById(userId: String) {
//        viewModelScope.launch {
//            val user = userRepository.getUserById(userId)
//            if(user != null){
//                _user.postValue(listOf(user))
//            }
//        }
//    }
//    fun getUserById(userId: String, callback: (user: User?) -> Unit) {
//        viewModelScope.launch {
//            val user = userRepository.getUserById(userId)
//            callback(user)
//        }
//    }
    fun getUserById(userId: String) {
        viewModelScope.launch {
            _user.postValue(userRepository.getUserById(userId))
        }
    }

    fun update(userId: String, username: String, email: String, phone: String){
        viewModelScope.launch {
//            var temp = userRepository.getUserById(userId)
//            temp.userUsername = username
//            temp.userEmail = email
//            temp.userPhone = phone
//
//            _user.postValue(temp)
        }
    }
}