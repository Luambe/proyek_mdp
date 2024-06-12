package com.example.code.TaskList

import android.app.ActivityManager.TaskDescription
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Attendance
import com.example.code.data.source.model.Task
import com.example.code.data.source.model.User
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {
    private val taskRepository = ManageApp.taskRepository
    private val userRepository = ManageApp.userRepository
    private val _task = MutableLiveData<List<Task>>()
    private val _user = MutableLiveData<User>()

    val tasks: LiveData<List<Task>>
        get() = _task
    val user: LiveData<User>
        get() = _user

    fun getTasks(userId: String) {
        viewModelScope.launch {
            val allTasks = taskRepository.getAllTasks(true)
            val filteredTasks = allTasks.filter { it.employeeId == userId || it.managerId == userId}
            val sortedTasks = filteredTasks.sortedBy { it.taskStatus == 1 }
            _task.postValue(sortedTasks)
        }
    }

}