package com.example.code.ManageEmployeeMenu

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
import com.example.code.data.source.model.User

class ManageEmployeeMenuFragment : Fragment() {
    val viewModel: ManageEmployeeMenuViewModel by viewModels<ManageEmployeeMenuViewModel>()
    lateinit var btnBack : Button
    lateinit var rvAttendance: RecyclerView
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_employee_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBack = view.findViewById(R.id.btn_back_manageEmployee)
        rvAttendance = view.findViewById(R.id.rv_employee_manageEmployee)

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val employeeAdapter = EmployeeAdapter(viewModel)
        rvAttendance.adapter = employeeAdapter

        employeeAdapter.submitList(ArrayList<User>())

        val employeeObserver: Observer<List<User?> > = Observer {
            employeeAdapter.submitList(it)
        }
        viewModel.users.observe(viewLifecycleOwner, employeeObserver)

        viewModel.getAllUser()

        rvAttendance.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvAttendance.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )

    }
}