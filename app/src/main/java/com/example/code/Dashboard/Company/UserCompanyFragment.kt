package com.example.code.Dashboard.Company

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.code.Login.LoginFragmentDirections
import com.example.code.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class UserCompanyFragment : Fragment() {

    lateinit var btnAdd : FloatingActionButton
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

        btnAdd.setOnClickListener {
            val action = UserCompanyFragmentDirections.actionUserCompanyFragmentToAddCompanyFragment()
            findNavController().navigate(action)
        }
    }
}