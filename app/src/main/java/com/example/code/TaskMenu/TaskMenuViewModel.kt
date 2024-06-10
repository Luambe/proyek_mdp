package com.example.code.TaskMenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.User
import kotlinx.coroutines.launch

class TaskMenuViewModel : ViewModel() {
    private val taskRepository = ManageApp.taskRepository
    private val _user = MutableLiveData<User?>(null)
    val user: LiveData<User?>
        get() = _user

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _status: MutableLiveData<String> = MutableLiveData<String>("idle")
    val status: LiveData<String>
        get() = _status

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
