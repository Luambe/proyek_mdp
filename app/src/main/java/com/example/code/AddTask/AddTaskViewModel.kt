package com.example.code.AddTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Task
import kotlinx.coroutines.launch

class AddTaskViewModel : ViewModel() {
    private val taskRepository = ManageApp.taskRepository
    private val _status = MutableLiveData<String?>(null)
    private val _task = MutableLiveData<Task?>(null)

    val task: LiveData<Task?>
        get() = _task

    val status: LiveData<String?>
        get() = _status

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