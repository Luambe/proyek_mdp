package com.example.code.AddTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Company
import com.example.code.data.source.model.Task
import com.example.code.data.source.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddTaskViewModel : ViewModel() {
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
    private val _users = MutableLiveData<List<User?>>(null)

    val company:LiveData<Company?>
        get() = _company

    val users:LiveData<List<User?>>
        get() = _users

    fun getCompany(id:String){
        viewModelScope.launch {
            _company.postValue(companyRepository.getCompanyById(id))
        }
    }

    fun getEmployee(companyId: String){
        viewModelScope.launch {
            _users.postValue(companyRepository.getEmployeeByCompanyId(companyId))
        }
    }


    //TASK
    private val taskRepository = ManageApp.taskRepository

    fun createTask(
        taskName: String,
        taskDescription: String,
        employeeId: String,
        managerId: String,
        taskStatus: Int
    ){
        _status.value = "processing"
        viewModelScope.launch{
            println(taskName)
            println(taskDescription)
            println(employeeId)
            println(managerId)
            println(taskStatus)
            taskRepository.createTask(
                taskName,
                taskDescription,
                employeeId,
                managerId,
                taskStatus
            )
            _status.postValue("success")
        }
    }

    fun getAllTask(
        taskName: String,
        taskDescription: String,
        employeeId: String,
        managerId: String,
        taskStatus: Int
    ){
        viewModelScope.launch {
//            _task.postValue(taskRepository.createTask(taskName, taskDescription, employeeId, managerId, taskStatus))
        }
    }
}