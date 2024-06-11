package com.example.code.TaskList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.data.source.model.Task
import com.example.code.databinding.TaskListItemBinding


class TaskDiffUtil: DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.taskId == newItem.taskId
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.taskName == newItem.taskName && oldItem.taskDescription == newItem.taskDescription
    }
}

class TasklistAdapter(
    val onClickListener: ((Task) -> Unit)? = null
) : ListAdapter<Task, TasklistAdapter.ViewHolder>(TaskDiffUtil()) {
    class ViewHolder(val binding: TaskListItemBinding): RecyclerView.ViewHolder(binding.root){
        val tvName: TextView = binding.tvTitle
        val tvDeskripsi: TextView = binding.tvDeskripsi
        val btnCheckTask: Button = binding.btnCheckTask
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:TaskListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.task_list_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)

        holder.tvName.text = task.taskName
        holder.tvDeskripsi.text = task.taskDescription

        holder.btnCheckTask.setOnClickListener {
            onClickListener?.invoke(task)
        }
    }

}