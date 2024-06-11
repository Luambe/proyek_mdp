package com.example.code.TaskMenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Company
import com.example.code.data.source.model.User
import kotlinx.coroutines.launch

class TaskMenuViewModel : ViewModel() {
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _status: MutableLiveData<String> = MutableLiveData<String>("idle")
    val status: LiveData<String>
        get() = _status


    //USER
    private val userRepository = ManageApp.userRepository
    private val _user = MutableLiveData<User>() // Ubah tipe data menjadi MutableLiveData<List<User>>
    val user: LiveData<User> // Ubah tipe LiveData menjadi List<User>
        get() = _user

    fun getUserById(userId: String) {
        viewModelScope.launch {
            _user.postValue(userRepository.getUserById(userId))
        }
    }


    //COMPANY
    private val companyRepository = ManageApp.companyRepository
    private val _company = MutableLiveData<Company?>(null)

    val company:LiveData<Company?>
        get() = _company

    fun getCompany(id:String){
        viewModelScope.launch {
            _company.postValue(companyRepository.getCompanyById(id))
        }
    }


    //TASK
    private val taskRepository = ManageApp.taskRepository

    fun createNewTask(
        taskName: String,
        taskDescription: String,
        employeeId: String,
        managerId: String,
        taskStatus: Int
    ){
        _status.value = "processing"
        viewModelScope.launch {
            try {
                taskRepository.createTask(
                    taskName, taskDescription, employeeId, managerId, taskStatus
                )
                _status.postValue("success")
                _error.postValue(null)
            } catch (e: Exception) {
                // Handle the error (e.g., show a toast or log the error)
                _error.postValue("Failed to create user: ${e}")
            }
        }
    }
}

