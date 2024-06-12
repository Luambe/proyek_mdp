package com.example.code.ManageEmployeeMenu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.code.data.source.model.Attendance
import com.example.code.databinding.DetailAttendanceListBinding
import com.example.code.R
import com.example.code.data.source.model.Task
import com.example.code.data.source.model.User
import com.example.code.databinding.DetailMenageemployeeItemBinding


class UserDiffUtil: DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userName == newItem.userName && oldItem.userRole == newItem.userRole
    }
}

class EmployeeAdapter(
    val viewModel: ManageEmployeeMenuViewModel,
) : ListAdapter<User, EmployeeAdapter.ViewHolder>(UserDiffUtil()) {
    class ViewHolder(val binding: DetailMenageemployeeItemBinding): RecyclerView.ViewHolder(binding.root){
        val tvName: TextView = binding.tvName
        val tvRole: TextView = binding.tvRole
        val btnPromote: Button = binding.btnPromote
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:DetailMenageemployeeItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.detail_menageemployee_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.tvName.text = user.userName
        holder.tvRole.text = user.userRole

        holder.btnPromote.setOnClickListener {
            viewModel.promote(user.userId)
            viewModel.getUser(user.userId)
            holder.tvName.text = user.userName
            holder.tvRole.text = user.userRole
        }

    }

}