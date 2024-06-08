package com.example.code.CompanyDashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.code.R

class CompanyDashboardFragment : Fragment() {

    companion object {
        fun newInstance() = CompanyDashboardFragment()
    }

    private lateinit var viewModel: CompanyDashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_company_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CompanyDashboardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}