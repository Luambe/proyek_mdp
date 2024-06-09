package com.example.code.Login

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.code.R

class LoginFragment : Fragment() {

    lateinit var btnGoToRegister : Button
    lateinit var btnLogin:Button
    lateinit var etUsername:EditText
    lateinit var etPassword: EditText
    val viewModel:LoginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGoToRegister = view.findViewById(R.id.btnGoToRegister)
        btnLogin = view.findViewById(R.id.btn_login_login)
        etUsername = view.findViewById(R.id.et_username_login)
        etPassword = view.findViewById(R.id.et_password_login)

        fun clearInput(){
            etUsername.text.clear()
            etPassword.text.clear()
        }

        btnGoToRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

//            if(username == ""){
//                Toast.makeText(requireContext(), "Username cannot be empty!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if(password == ""){
//                Toast.makeText(requireContext(), "Password cannot be empty!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            viewModel.getAllUser()





//            viewModel.login(username, password)
//
//            viewModel.status.observe(viewLifecycleOwner, Observer {
//                if(it == "success"){
//                    Toast.makeText(requireContext(), "login success", Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(requireContext(), "login gagal", Toast.LENGTH_SHORT).show()
//                }
//            })
//            goToActivityDashboard()
//            clearInput()
        }
    }


    private lateinit var navigationListener: NavigationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener) {
            navigationListener = context as NavigationListener
        } else {
            throw RuntimeException("Activity must implement NavigationListener")
        }
    }

    // Trigger navigation from FragmentA
    fun goToActivityDashboard() {
        navigationListener.navigateToActivityDashboard()
    }

}

interface NavigationListener {
    fun navigateToActivityDashboard()
}