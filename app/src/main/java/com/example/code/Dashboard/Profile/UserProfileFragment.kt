package com.example.code.Dashboard.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.code.R
import com.example.code.data.source.model.User
import com.example.code.databinding.FragmentUserCompanyBinding

class UserProfileFragment : Fragment() {
    lateinit var etUsername: EditText
    lateinit var etEmail: EditText
    lateinit var etPhone: EditText
    lateinit var btnSave: Button
    var user: User? = null
    val viewModel:UserProfileViewModel by viewModels<UserProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etUsername = view.findViewById(R.id.et_username_userProfile)
        etEmail = view.findViewById(R.id.et_email_userProfile)
        etPhone = view.findViewById(R.id.et_phone_userProfile)
        btnSave = view.findViewById(R.id.btn_save_userProfile)

        val userId = UserProfileFragmentArgs.fromBundle(requireArguments()).userId

        println(userId)

        viewModel.getUserById(userId)

        viewModel.user.observe(viewLifecycleOwner, Observer{
            user = it
            etUsername.setText(it.userUsername)
            etEmail.setText(it.userEmail)
            etPhone.setText(it.userPhone)
        })

        btnSave.setOnClickListener {
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()
//            viewModel.update(userId, username, email, phone){
//
//            }

        }
    }
}