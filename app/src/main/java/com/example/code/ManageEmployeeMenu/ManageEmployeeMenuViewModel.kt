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
    private val companyRepository = ManageApp.companyRepository
    private val userRepository = ManageApp.userRepository
    private val _employee = MutableLiveData<List<User>>()
    private val _user = MutableLiveData<User>()
    private val _status = MutableLiveData<String>()


    val employee: LiveData<List<User>>
        get() = _employee

    val user: LiveData<User>
        get() = _user

    val status: LiveData<String>
        get() = _status


    fun getEmployee(companyId : String){
        viewModelScope.launch {
            println("INI DI SINI INININININININNIIN")
            println(companyId)
            _employee.postValue(companyRepository.getEmployeeFromCompany(companyId))
        }
    }

    fun getUser(userId: String) {
        viewModelScope.launch {
            println("Ini userId dari siinjang : $userId")
            _user.postValue(userRepository.getUserById(userId))
            _status.postValue("sukses")
        }
    }

    fun promote(userId:String){
        viewModelScope.launch {
            userRepository.promoteUser(userId)
        }
    }
}