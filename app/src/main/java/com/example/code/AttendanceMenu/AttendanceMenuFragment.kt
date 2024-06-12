package com.example.code.AttendanceMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.code.CompanyDashboard.CompanyDashboardViewModel
import com.example.code.R

class AttendanceMenuFragment : Fragment() {

    lateinit var tvCompany: TextView
    lateinit var card_absence:CardView
    lateinit var card_manage_attendance:CardView
    lateinit var btnBack:Button
    val viewModel: AttendanceMenuViewModel by viewModels<AttendanceMenuViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attendance_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCompany = view.findViewById(R.id.tv_company_attendanceMenu)
        card_absence = view.findViewById(R.id.card_absence)
        card_manage_attendance = view.findViewById(R.id.card_manage_attendance)
        btnBack = view.findViewById(R.id.btnBack)

        val userId = AttendanceMenuFragmentArgs.fromBundle(requireArguments()).userId
        var companyId: String?= null

        viewModel.getUserById(userId)

        viewModel.user.observe(viewLifecycleOwner, Observer{
            if(it.userRole != null) {
                companyId = it.companyId
            }

            companyId?.let{id ->
                viewModel.getCompany(id)
            }

            viewModel.company.observe(viewLifecycleOwner, Observer{company->
                tvCompany.setText(company?.companyName)
            })
        })

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

        btnBack.setOnClickListener {
            userId?.let { safeUserId ->
                val action = AttendanceMenuFragmentDirections.actionGlobalCompanyDashboardFragment(safeUserId)
                findNavController().navigate(action)
            }
        }
    }

}