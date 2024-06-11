package com.example.code.AttendanceMenu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.code.CompanyDashboard.CompanyDashboardFragmentDirections
import com.example.code.Dashboard.Home.UserHomeFragmentDirections
import com.example.code.Dashboard.Profile.UserProfileFragmentArgs
import com.example.code.R

class AttendanceMenuFragment : Fragment() {

    private lateinit var viewModel: AttendanceMenuViewModel
    lateinit var card_absence:CardView
    lateinit var card_manage_attendance:CardView
    lateinit var btn_back_company_dashboard:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attendance_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        card_absence = view.findViewById(R.id.card_absence)
        card_manage_attendance = view.findViewById(R.id.card_manage_attendance)
        btn_back_company_dashboard = view.findViewById(R.id.btn_back_company_dashboard)

        val userId = AttendanceMenuFragmentArgs.fromBundle(requireArguments()).userId

        card_absence.setOnClickListener {
            userId?.let { safeUserId ->
                val action = AttendanceMenuFragmentDirections.actionGlobalAbsenceFragment2(safeUserId)
                findNavController().navigate(action)
            }
        }

        card_manage_attendance.setOnClickListener {
            userId?.let { safeUserId ->
                val action = AttendanceMenuFragmentDirections.actionGlobalManageAttendanceFragment(safeUserId)
                findNavController().navigate(action)
            }
        }

        btn_back_company_dashboard.setOnClickListener {
            userId?.let { safeUserId ->
                val action = AttendanceMenuFragmentDirections.actionGlobalCompanyDashboardFragment(safeUserId)
                findNavController().navigate(action)
            }
        }
    }

}