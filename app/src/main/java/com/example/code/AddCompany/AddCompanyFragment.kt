package com.example.code.AddCompany

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.code.Dashboard.Company.UserCompanyViewModel
import com.example.code.R
import com.example.code.data.source.model.Company

class AddCompanyFragment : Fragment() {

    lateinit var btnBack : Button
    lateinit var btnSubmit : Button
    lateinit var et_company_name : EditText
    lateinit var et_company_pass : EditText
    lateinit var et_company_desc : EditText
    val viewModel: AddCompanyViewModel by viewModels<AddCompanyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_company, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBack = view.findViewById(R.id.btnBack)
        btnSubmit = view.findViewById(R.id.btnSubmit)
        et_company_name = view.findViewById(R.id.et_company_name)
        et_company_pass = view.findViewById(R.id.et_company_pass)
        et_company_desc = view.findViewById(R.id.et_company_desc)

        btnSubmit.setOnClickListener {
            if(et_company_name.text.toString() == "" || et_company_pass.text.toString() == "" || et_company_desc.text.toString() == ""){
                Toast.makeText(view.context, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            }else{
                val company = Company("6", "${et_company_name.text}", "user yang sedang login", "${et_company_pass.text}")
                viewModel.createCompany(company)
            }
        }

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if(it == "success"){
                findNavController().popBackStack()
            }
        })

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}