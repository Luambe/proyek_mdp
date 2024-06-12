package com.example.code.TaskDetail

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.code.CompanyDashboard.CompanyDashboardFragmentArgs
import com.example.code.CompanyDashboard.CompanyDashboardViewModel
import com.example.code.R
import com.example.code.TaskList.TaskListFragmentArgs

class TaskDetailFragment : Fragment() {
    lateinit var tvTaskTitle: TextView
    lateinit var tvTaskDesc: TextView
    lateinit var tvTaskStatus: TextView
    lateinit var btn_back_task_list: Button
    lateinit var btn_task_done: Button

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
        btn_back_task_list = view.findViewById(R.id.btn_back_task_list)
        btn_task_done = view.findViewById(R.id.btn_task_done)

        btn_task_done.isVisible = true
        btn_task_done.isClickable = true

        val userId = TaskDetailFragmentArgs.fromBundle(requireArguments()).userId
        val taskId = TaskDetailFragmentArgs.fromBundle(requireArguments()).taskId
        viewModel.getTask(taskId)
        viewModel.td.observe(viewLifecycleOwner, Observer {
            println("isi td observer")
            println(it)
            tvTaskTitle.setText(it?.taskName.toString())
            tvTaskDesc.setText(it?.taskDescription.toString())

            if(it?.taskStatus == 0){
                tvTaskStatus.setText("Assigned")
                tvTaskStatus.setTextColor(Color.parseColor("#E60808"))
            }
            else if(it?.taskStatus == 1){
                tvTaskStatus.setText("Done")
                tvTaskStatus.setTextColor(Color.parseColor("#00FF0D"))
                btn_task_done.isVisible = false
                btn_task_done.isClickable = false
            }

            if(it?.managerId == userId){
                btn_task_done.isVisible = false
                btn_task_done.isClickable = false
            }
        })

        btn_back_task_list.setOnClickListener {
            findNavController().popBackStack()
        }

        btn_task_done.setOnClickListener {
            viewModel.td.observe(viewLifecycleOwner, Observer {
                if(it?.employeeId == userId){
                    viewModel.updateTask(it.taskId)
                    Toast.makeText(requireContext(), "Task Submitted", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            })

            viewModel.getTask(taskId)
            viewModel.td.observe(viewLifecycleOwner, Observer {
                tvTaskTitle.setText(it?.taskName.toString())
                tvTaskDesc.setText(it?.taskDescription.toString())

                if(it?.taskStatus == 0){
                    tvTaskStatus.setText("Assigned")
                    tvTaskStatus.setTextColor(Color.parseColor("#E60808"))
                }
                else if(it?.taskStatus == 1){
                    tvTaskStatus.setText("Done")
                    tvTaskStatus.setTextColor(Color.parseColor("#00FF0D"))
                    btn_task_done.isVisible = false
                    btn_task_done.isClickable = false
                }

                if(it?.managerId == userId){
                    btn_task_done.isVisible = false
                    btn_task_done.isClickable = false
                }
            })
        }
    }

}