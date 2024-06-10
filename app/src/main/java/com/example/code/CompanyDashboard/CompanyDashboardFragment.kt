package com.example.code.CompanyDashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.code.R

class CompanyDashboardFragment : Fragment() {

    lateinit var btn_back_main_menu: Button
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

        btn_back_main_menu.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}