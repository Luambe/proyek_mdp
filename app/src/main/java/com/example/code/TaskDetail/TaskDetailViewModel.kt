package com.example.code.TaskDetail

import android.app.ActivityManager.TaskDescription
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Task
import com.example.code.data.source.model.TaskDetail
import kotlinx.coroutines.launch

class TaskDetailViewModel : ViewModel() {
    private val taskRepository = ManageApp.taskRepository
    private val _status = MutableLiveData<String?>(null)
    private val _td = MutableLiveData<Task?>(null)

    val status:LiveData<String?>
        get() = _status

    val td:LiveData<Task?>
        get() = _td

    fun getTask(taskId: String){
        viewModelScope.launch {
            println("isi isi fetchTasknya : ${fetchTask(taskId)}")
            _td.postValue(fetchTask(taskId))
        }
    }

    suspend fun fetchTask(taskId: String):Task?{
        var task:Task? = null
        viewModelScope.launch {
            _td.postValue(taskRepository.getTaskById(taskId))
            println(taskRepository.getTaskById(taskId))
            _status.postValue("success")

            task = taskRepository.getTaskById(taskId)
        }
        return task
    }
}