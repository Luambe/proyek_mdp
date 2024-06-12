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
import com.example.code.Dashboard.Company.UserCompanyFragmentArgs
import com.example.code.ManageAttendance.AttendanceAdapter
import com.example.code.ManageAttendance.ManageAttendanceViewModel
import com.example.code.R
import com.example.code.data.source.model.Attendance
import com.example.code.data.source.model.User

class ManageEmployeeMenuFragment : Fragment() {
    val viewModel: ManageEmployeeMenuViewModel by viewModels<ManageEmployeeMenuViewModel>()
    lateinit var btn_back_manage_employee : Button
    lateinit var rv_employee_manageEmployee: RecyclerView
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_employee_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_back_manage_employee = view.findViewById(R.id.btn_back_manage_employee)
        rv_employee_manageEmployee = view.findViewById(R.id.rv_employee_manageEmployee)

        val userId = ManageEmployeeMenuFragmentArgs.fromBundle(requireArguments()).userId

        btn_back_manage_employee.setOnClickListener {
            val action = ManageEmployeeMenuFragmentDirections.actionGlobalCompanyDashboardFragment(userId)
            findNavController().navigate(action)
        }


        println(userId)
        viewModel.getUser(userId)
        viewModel.status.observe(viewLifecycleOwner, Observer {
            println("masuk status observer")
            if(it == "sukses"){
                println("Aman Boss Ku")
            }
            else{
                println("RED FLAG LU")
            }
        })
        println("ini user dakjal")
        println(viewModel.user.value)
        viewModel.user.observe(viewLifecycleOwner, Observer {
            println("ini company dakjal : $it")
            viewModel.getEmployee(it.companyId.toString())
        })
//
        val employeeAdapter = EmployeeAdapter(viewModel)
        rv_employee_manageEmployee.adapter = employeeAdapter
//
////        println("Debug 1")
//        employeeAdapter.submitList(ArrayList<User>())
//
////        println("Debug 2")
//
        val employeeObserver: Observer<List<User>> = Observer {
            employeeAdapter.submitList(it)
        }
        viewModel.employee.observe(viewLifecycleOwner, employeeObserver)
        println("Debug 4")
        viewModel.user.observe(viewLifecycleOwner, Observer {
            viewModel.getEmployee(it.companyId.toString())
        })
//
        rv_employee_manageEmployee.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_employee_manageEmployee.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )

    }
}