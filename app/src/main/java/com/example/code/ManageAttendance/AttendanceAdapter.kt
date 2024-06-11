package com.example.code.ManageAttendance

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.code.data.source.model.Attendance
import com.example.code.databinding.DetailAttendanceListBinding
import com.example.code.R


class AttedanceDiffUtil: DiffUtil.ItemCallback<Attendance>(){
    override fun areItemsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
        return oldItem.attendanceId == newItem.attendanceId
    }

    override fun areContentsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
        return oldItem.userId == newItem.userId && oldItem.attendanceStatus == newItem.attendanceStatus
    }
}

class AttendanceAdapter(
    val viewModel: ManageAttendanceViewModel,
    val getUsername: (String) -> Unit
) : ListAdapter<Attendance, AttendanceAdapter.ViewHolder>(AttedanceDiffUtil()) {
    class ViewHolder(val binding: DetailAttendanceListBinding): RecyclerView.ViewHolder(binding.root){
        val tvName: TextView = binding.tvName
        val tvStatus: TextView = binding.tvStatus
        val imageStatus: ImageView = binding.imageStatus
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:DetailAttendanceListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.detail_attendance_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val attendance = getItem(position)
        println("Debug 3")
        println(attendance.userId)
        holder.tvName.text = "Loading..."  // Placeholder text while fetching the username

        viewModel.getUser(attendance.userId.toString())
        viewModel.user.observeForever { user ->
            if (user != null && user.userId == attendance.userId) {
                holder.tvName.text = user.userName
            }
        }
        holder.tvStatus.text = if (attendance.attendanceStatus.toString() == "1") "Late" else "On Time"
        if(attendance.attendanceStatus.toString() == "1"){
            holder.imageStatus.setBackgroundResource(R.drawable.baseline_assignment_late_24)
        }else{
            holder.imageStatus.setBackgroundResource(R.drawable.baseline_check_24)
        }
    }

}