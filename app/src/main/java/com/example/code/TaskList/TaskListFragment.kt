package com.example.code.TaskList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.code.ManageAttendance.AttendanceAdapter
import com.example.code.ManageAttendance.ManageAttendanceViewModel
import com.example.code.R
import com.example.code.data.source.model.Attendance
import com.example.code.data.source.model.Task

class TaskListFragment : Fragment() {

    val viewModel: TaskListViewModel by viewModels<TaskListViewModel>()
    lateinit var rvTaskList: RecyclerView
    lateinit var btn_back_task: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTaskList = view.findViewById(R.id.rvTaskList)
        btn_back_task = view.findViewById(R.id.btn_back_task)

        val tasklistAdapter = TasklistAdapter{
            val action = TaskListFragmentDirections.actionGlobalTaskDetailFragment()
            findNavController().navigate(action)
            viewModel.getTasks()
        }
        rvTaskList.adapter = tasklistAdapter

        tasklistAdapter.submitList(ArrayList<Task>())

        val tasklistObserver: Observer<List<Task>> = Observer {
            tasklistAdapter.submitList(it)
        }
        viewModel.tasks.observe(viewLifecycleOwner, tasklistObserver)

        viewModel.getTasks()

        rvTaskList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvTaskList.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )

        btn_back_task.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}