package com.example.code.Register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.code.R

class RegisterFragment : Fragment() {

    lateinit var btnGoToLogin : Button
    lateinit var btnRegister: Button
    lateinit var etName: EditText
    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var etConfirm: EditText
    lateinit var etEmail: EditText
    lateinit var etPhone: EditText
    lateinit var rbOwner: RadioButton
    lateinit var rbEmployee: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGoToLogin = view.findViewById(R.id.btnGoToLogin)
        btnRegister = view.findViewById(R.id.btn_register_register)
        etName = view.findViewById(R.id.et_name_register)
        etUsername = view.findViewById(R.id.et_username_register)
        etPassword = view.findViewById(R.id.et_password_register)
        etConfirm = view.findViewById(R.id.et_confirm_register)
        etEmail = view.findViewById(R.id.et_email_register)
        etPhone = view.findViewById(R.id.et_phone_register)
        rbOwner = view.findViewById(R.id.rb_owner_register)
        rbEmployee = view.findViewById(R.id.rb_employee_register)
        var role = ""

        btnGoToLogin.setOnClickListener {
            findNavController().popBackStack()
        }

        rbOwner.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                role = "Owner"
            }
        }

        rbEmployee.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                role = "Employee"
            }
        }

        btnRegister.setOnClickListener{
            val name = etName.text.toString()
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val confirm = etConfirm.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()

            if(name == "") {
                Toast.makeText(requireContext(), "Name cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(username == "") {
                Toast.makeText(requireContext(), "Username cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password == "") {
                Toast.makeText(requireContext(), "Password cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(confirm != password) {
                Toast.makeText(requireContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(email == "") {
                Toast.makeText(requireContext(), "Email cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(phone == "") {
                Toast.makeText(requireContext(), "Phone cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(phone.length != 12){
                Toast.makeText(requireContext(), "Phone must be 12 digits", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(role == ""){
                Toast.makeText(requireContext(), "Role cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
    }
}