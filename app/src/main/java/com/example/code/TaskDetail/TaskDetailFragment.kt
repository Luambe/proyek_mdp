package com.example.code.TaskDetail

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.code.CompanyDashboard.CompanyDashboardFragmentArgs
import com.example.code.CompanyDashboard.CompanyDashboardViewModel
import com.example.code.R

class TaskDetailFragment : Fragment() {
    lateinit var tvTaskTitle: TextView
    lateinit var tvTaskDesc: TextView
    lateinit var tvTaskStatus: TextView

    val viewModel: TaskDetailViewModel by viewModels<TaskDetailViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTaskTitle = view.findViewById(R.id.tvTaskTitle)
        tvTaskDesc = view.findViewById(R.id.tvTaskDesc)
        tvTaskStatus = view.findViewById(R.id.tvTaskStatus)

        val taskId = TaskDetailFragmentArgs.fromBundle(requireArguments()).taskId
        viewModel.getTask(taskId)
        viewModel.td.observe(viewLifecycleOwner, Observer {
            tvTaskTitle.setText(it?.taskName.toString())
            tvTaskDesc.setText(it?.taskDescription.toString())

            if(it?.taskStatus == 0){
                tvTaskTitle.setText("Assigned")
                tvTaskTitle.setTextColor(Color.parseColor("#E60808"))
            }
            else if(it?.taskStatus == 1){
                tvTaskTitle.setText("Done")
                tvTaskTitle.setTextColor(Color.parseColor("#00FF0D"))
            }
        })
    }

}