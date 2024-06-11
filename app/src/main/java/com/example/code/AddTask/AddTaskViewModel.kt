package com.example.code.AddTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Company
import com.example.code.data.source.model.Task
import com.example.code.data.source.model.User
import kotlinx.coroutines.launch

class AddTaskViewModel : ViewModel() {

    private val taskRepository = ManageApp.taskRepository
    private val companyRepository = ManageApp.companyRepository
    private val userRepository = ManageApp.userRepository
    private val _status = MutableLiveData<String?>(null)
    private val _user = MutableLiveData<User>()
    private val _company = MutableLiveData<Company>()
    private val _employee = MutableLiveData<List<User>>()
    private val _task = MutableLiveData<Task?>(null)

    val task: LiveData<Task?>
        get() = _task

    val status: LiveData<String?>
        get() = _status

    val user: LiveData<User>
        get() = _user


    val company: LiveData<Company>
        get() = _company

    val employee: LiveData<List<User>>
        get() = _employee

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

    fun createTask(
        taskName: String,
        taskDescription: String,
        employeeId: String,
        managerId: String,
        taskStatus: Int
    ){
        viewModelScope.launch {
            _task.postValue(taskRepository.createTask(taskName, taskDescription, employeeId, managerId, taskStatus))
        }
    }

    fun getCompany(id:String){
        viewModelScope.launch {
            _company.postValue(companyRepository.getCompanyById(id))
        }
    }

    fun getUserById(userId: String) {
        viewModelScope.launch {
            _user.postValue(userRepository.getUserById(userId))
        }
    }


    fun getEmployee(companyId: String){
        viewModelScope.launch{
            try {
                val employees = companyRepository.getEmployeeFromCompany(companyId)
                _employee.postValue(employees)
            } catch (e: Exception) {
                // Log the exception
                e.printStackTrace()
                // Optionally update the status LiveData to notify observers of the error
                _status.postValue("Failed to fetch employees: ${e.message}")
            }
        }
    }
}