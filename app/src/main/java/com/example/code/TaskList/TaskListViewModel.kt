package com.example.code.TaskList

import android.app.ActivityManager.TaskDescription
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Task
import com.example.code.data.source.model.User
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {
    private val taskRepository = ManageApp.taskRepository
    private val _status = MutableLiveData<String?>(null)
    private val _tasks = MutableLiveData<List<Task?>>(null)

    val tasks: LiveData<List<Task?>>
        get() = _tasks

    val status:LiveData<String?>
        get() = _status

    fun getAllTask(
    ){
        viewModelScope.launch {
            _tasks.postValue(taskRepository.getAllTasks(true))
        }
    }
}