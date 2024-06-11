package com.example.code.AddTask

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.code.Dashboard.Profile.UserProfileFragmentArgs
import com.example.code.Dashboard.Profile.UserProfileViewModel
import com.example.code.R

class AddTaskFragment : Fragment() {
    val viewModel: AddTaskViewModel by viewModels<AddTaskViewModel>()

    lateinit var tvCompany: TextView
    lateinit var etName: EditText
    lateinit var etDesc: EditText
    lateinit var etDue: EditText
    lateinit var btnSubmit: Button
    lateinit var btnBack: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCompany = view.findViewById(R.id.tv_company_addTask)
        etName = view.findViewById(R.id.et_name_addTask)
        etDesc = view.findViewById(R.id.et_desc_addTask)
        etDue = view.findViewById(R.id.et_due_addTask)
        btnSubmit = view.findViewById(R.id.btn_submit_addTask)
        btnBack = view.findViewById(R.id.btn_back_addTask)

        val userId = AddTaskFragmentArgs.fromBundle(requireArguments()).userId

        println(userId)

        viewModel.getUserById(userId)

        var companyId: String?= null

        viewModel.user.observe(viewLifecycleOwner, Observer{
            if(it.userRole != null) {
                println("pasti ${it.companyId}")
                companyId = it.companyId
            }

            companyId?.let{id ->
                viewModel.getCompany(id)
            }

            viewModel.company.observe(viewLifecycleOwner, Observer{company->
                tvCompany.setText(company?.companyName)
                Toast.makeText(requireContext(), "${company?.companyName}", Toast.LENGTH_SHORT).show()
            })
        })

        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            val desc = etDesc.text.toString()
            val due = etDue.text.toString()

            if(name == ""){
                Toast.makeText(requireContext(), "Task Name cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(desc == ""){
                Toast.makeText(requireContext(), "Task Description cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(due == ""){
                Toast.makeText(requireContext(), "Due Date cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            println("company id: ${companyId}")
            viewModel.getEmployee(companyId.toString())

            viewModel.users.observe(viewLifecycleOwner, Observer{
                for (i in it){
                    try {
                        viewModel.createTask(name, desc, i!!.userId, userId, 0)
                        Toast.makeText(view.context, "Success", Toast.LENGTH_SHORT).show()
                    }catch (e: Exception){
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        }

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}