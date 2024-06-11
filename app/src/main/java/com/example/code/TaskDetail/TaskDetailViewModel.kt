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
    private val taskDetailRepository = ManageApp.taskDetailRepository
    private val _status = MutableLiveData<String?>(null)
    private val _td = MutableLiveData<TaskDetail?>(null)

    val status:LiveData<String?>
        get() = _status

    val td:LiveData<TaskDetail?>
        get() = _td

    fun addNewTaskDetail(
        tdName:String,
        tdDescription: String,
        taskId:String,
        tdStatus:Boolean
    ){
        viewModelScope.launch {
            taskDetailRepository.createTaskDetail(tdName,tdDescription,taskId,tdStatus)
        }
    }
}