package com.example.code.CompanyDashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.code.AttendanceMenu.AttendanceMenuFragmentDirections
import com.example.code.Dashboard.Profile.UserProfileFragmentArgs
import com.example.code.R

class CompanyDashboardFragment : Fragment() {

    lateinit var btn_back_main_menu: Button
    lateinit var card_attendance: CardView
    lateinit var card_task:CardView
    lateinit var card_employee:CardView
    lateinit var tvAnnouncement: TextView
    lateinit var etAnnouncement: EditText
    lateinit var btnEdit: Button
    lateinit var btnCencel: Button

    var announcement_text: String = ""
    var companyId: String = "bc0f9eeb-09d3-4d31-9a61-1dcb328de5a9"
    var nowedit: Boolean = false
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
        tvAnnouncement = view.findViewById(R.id.tvAnnouncement)
        etAnnouncement = view.findViewById(R.id.etAnnouncement)
        btnEdit = view.findViewById(R.id.btnEdit)
        btnCencel = view.findViewById(R.id.btnCencel)

        etAnnouncement.isVisible = false
        btnCencel.isClickable = false

        viewModel.getAnnouncement(companyId)
        viewModel.announcement.observe(viewLifecycleOwner, Observer {
            announcement_text = it
            tvAnnouncement.setText(announcement_text)
            etAnnouncement.setText(announcement_text)
        })

//        val userId = UserProfileFragmentArgs.fromBundle(requireArguments()).userId
        val userId = "ASUKAMU"

        btn_back_main_menu.setOnClickListener {
            findNavController().popBackStack()
        }

        btnEdit.setOnClickListener {
            if(!nowedit){
                etAnnouncement.isVisible = true
                tvAnnouncement.isVisible = false
                btnEdit.isClickable = false
            }
            else{
                tvAnnouncement.setText(etAnnouncement.text)
                etAnnouncement.isVisible = false
                tvAnnouncement.isVisible = true
            }
        }

        etAnnouncement.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                // Ini akan dipanggil setelah teks berubah
                println("Text changed to: ${s.toString()}")
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Ini akan dipanggil sebelum teks berubah
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(etAnnouncement.text != tvAnnouncement.text){
                    btnEdit.setBackgroundResource(android.R.drawable.ic_menu_save)
                    if(!btnEdit.isClickable){
                        btnEdit.isClickable = true
                    }
                }
                else{
                    btnEdit.setBackgroundResource(android.R.drawable.ic_menu_edit)
                    btnEdit.isClickable = false
                }
            }
        })

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