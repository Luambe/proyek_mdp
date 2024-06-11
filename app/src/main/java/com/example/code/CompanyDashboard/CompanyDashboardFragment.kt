package com.example.code.CompanyDashboard

import android.content.res.ColorStateList
import android.graphics.Color
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
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
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
    lateinit var tvCompany: TextView
    lateinit var tvAnnouncement: TextView
    lateinit var etAnnouncement: EditText
    lateinit var btnEdit: ImageButton
    lateinit var btnCencel: ImageButton

    var announcement_text: String = ""
    var nowedit: Boolean = false
//    var companyId: String = "81efffff-b3f5-477e-9507-d338b584e36a" //CompanyID RAMA
//    var companyId: String = "bc0f9eeb-09d3-4d31-9a61-1dcb328de5a9" //CompanyID Steven
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
        tvCompany = view.findViewById(R.id.tv_company_companyDashboard)
        tvAnnouncement = view.findViewById(R.id.tvAnnouncement)
        etAnnouncement = view.findViewById(R.id.etAnnouncement)
        btnEdit = view.findViewById(R.id.btnEdit)
        btnCencel = view.findViewById(R.id.btnCencel)

        etAnnouncement.isVisible = false
        btnCencel.isClickable = false

        println("sebelum")

        val userId = CompanyDashboardFragmentArgs.fromBundle(requireArguments()).userId
        var companyId: String?= null

        println("userid: ${userId}")

        viewModel.getUserById(userId)

        viewModel.user.observe(viewLifecycleOwner, Observer{
            println("company id:")
            println(it.companyId)
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

//        viewModel.company.observe(viewLifecycleOwner, Observer {
//            println("sesudah")
//            println("Company id:")
//            println(it)
//            if (it != null) {
//                viewModel.getAnnouncement(it.companyId) // Call getAnnouncement once with company data
//                println("lahhhhhhh")
//            }
//        })
//
//        var companyIdRetrieved = false // Flag to track if companyId is already set
//
//        viewModel.company.observe(viewLifecycleOwner, Observer { company ->
//            println("company:")
//            println(company)
//            if (company != null && !companyIdRetrieved) {
//                companyId = company.companyId
//                viewModel.getAnnouncement(companyId.toString())
//                companyIdRetrieved = true // Set flag after first call
//            }
//        })

        btn_back_main_menu.setOnClickListener {
            findNavController().popBackStack()
        }

        btnEdit.setOnClickListener {
            if(!nowedit){
                etAnnouncement.isVisible = true
                tvAnnouncement.isVisible = false
                btnEdit.isClickable = false
                btnEdit.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#007281"))
                btnCencel.isClickable = true
                nowedit = true
            }
            else{

                tvAnnouncement.setText(etAnnouncement.text.toString())
                try {
                    viewModel.updateAnnouncement(companyId.toString(), etAnnouncement.text.toString())
                    Toast.makeText(view.context, "Success", Toast.LENGTH_SHORT).show()
                }catch (e: Exception){
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
                etAnnouncement.isVisible = false
                tvAnnouncement.isVisible = true
                btnCencel.isClickable = false
                btnEdit.setImageResource(android.R.drawable.ic_menu_edit)
                btnEdit.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#00E1FF"))
                nowedit = false
            }
        }

        btnCencel.setOnClickListener {
            etAnnouncement.setText(tvAnnouncement.text.toString())
            btnEdit.setImageResource(android.R.drawable.ic_menu_edit)
            btnEdit.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#00E1FF"))
            etAnnouncement.isVisible = false
            tvAnnouncement.isVisible = true
            btnEdit.isClickable = true
            btnCencel.isClickable = false
            nowedit = false
        }

        etAnnouncement.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                // Ini akan dipanggil setelah teks berubah
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Ini akan dipanggil sebelum teks berubah
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(nowedit) {
                    if (etAnnouncement.text.toString() != tvAnnouncement.text.toString()) {
                        btnEdit.setImageResource(android.R.drawable.ic_menu_save)
                        btnEdit.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#00E1FF"))
                        if (!btnEdit.isClickable) {
                            btnEdit.isClickable = true
                        }
                    } else if (etAnnouncement.text.toString() == tvAnnouncement.text.toString()){
                        btnEdit.setImageResource(android.R.drawable.ic_menu_edit)
                        btnEdit.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#007281"))
                        btnEdit.isClickable = false
                    }
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
            userId?.let { safeUserId ->
                val action =
                    CompanyDashboardFragmentDirections.actionGlobalTaskMenuFragment(safeUserId)
                findNavController().navigate(action)
            }
        }

        card_employee.setOnClickListener {
            val action = CompanyDashboardFragmentDirections.actionGlobalManageEmployeeMenuFragment()
            findNavController().navigate(action)
        }
    }

}