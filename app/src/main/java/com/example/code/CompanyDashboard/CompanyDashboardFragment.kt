package com.example.code.CompanyDashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.code.AttendanceMenu.AttendanceMenuFragmentDirections
import com.example.code.Dashboard.Profile.UserProfileFragmentArgs
import com.example.code.R

class CompanyDashboardFragment : Fragment() {

    lateinit var btn_back_main_menu: Button
    lateinit var card_attendance: CardView
    lateinit var card_task:CardView
    lateinit var card_employee:CardView
    val viewModel:CompanyDashboardViewModel by viewModels<CompanyDashboardViewModel>()
//    val navArgs:CompanyDashboardArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_company_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_back_main_menu = view.findViewById(R.id.btn_back_main_menu)
        card_attendance = view.findViewById(R.id.card_attendance)
        card_task = view.findViewById(R.id.card_task)
        card_employee = view.findViewById(R.id.card_employee)

        val userId = UserProfileFragmentArgs.fromBundle(requireArguments()).userId

        btn_back_main_menu.setOnClickListener {
            findNavController().popBackStack()
        }

        card_attendance.setOnClickListener {
            userId?.let { safeUserId ->
                val action = CompanyDashboardFragmentDirections.actionGlobalAttendanceMenuFragment(safeUserId)
                findNavController().navigate(action)
            }
        }

        card_task.setOnClickListener {
            val action = CompanyDashboardFragmentDirections.actionGlobalTaskMenuFragment()
            findNavController().navigate(action)
        }

        card_employee.setOnClickListener {
            val action = CompanyDashboardFragmentDirections.actionGlobalManageEmployeeMenuFragment()
            findNavController().navigate(action)
        }
    }

}