package com.example.code.ManageEmployeeMenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Task
import com.example.code.data.source.model.User
import kotlinx.coroutines.launch

class ManageEmployeeMenuViewModel : ViewModel() {
    private val userRepository = ManageApp.userRepository
    private val _status = MutableLiveData<String?>(null)
    private val _users = MutableLiveData<List<User?>>(null)
    private val _user = MutableLiveData<User?>(null)

    val users: LiveData<List<User?>>
        get() = _users

    val status: LiveData<String?>
        get() = _status

    val user: LiveData<User?>
        get() = _user

    fun getAllUser(){
        viewModelScope.launch {
            _users.postValue(userRepository.getAllUsers(forceUpdate = true))
        }
    }

    fun promote(userId:String){
        viewModelScope.launch {
            _user.postValue(userRepository.promoteUser(userId))
        }
    }
}