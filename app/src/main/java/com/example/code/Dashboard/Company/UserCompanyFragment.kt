package com.example.code.Dashboard.Company

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.code.Login.LoginFragmentDirections
import com.example.code.R
import com.example.code.data.source.model.Company
import com.google.android.material.floatingactionbutton.FloatingActionButton


class UserCompanyFragment : Fragment() {

    lateinit var btnAdd : FloatingActionButton
    lateinit var rvCompany : RecyclerView
    lateinit var layoutmanage : LayoutManager
    lateinit var companyAdapter : CompanyAdapter
    val viewModel:UserCompanyViewModel by viewModels<UserCompanyViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_company, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAdd = view.findViewById(R.id.btnAdd)
        rvCompany = view.findViewById(R.id.rvCompany)

        companyAdapter = CompanyAdapter{
            val action = UserCompanyFragmentDirections.actionGlobalCompanyDashboardFragment()
            findNavController().navigate(action)
        }

        rvCompany.adapter = companyAdapter

        companyAdapter.submitList(ArrayList<Company>())

        val companiesObserver:Observer<List<Company>> = Observer{
            companyAdapter.submitList(it)
        }
        viewModel.companies.observe(viewLifecycleOwner, companiesObserver)

        viewModel.getCompanies()

        rvCompany.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        btnAdd.setOnClickListener {
            val action = UserCompanyFragmentDirections.actionUserCompanyFragmentToAddCompanyFragment()
            findNavController().navigate(action)
            viewModel.getCompanies()
        }
    }
}