package com.example.code.Dashboard.Company

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.marginTop
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.code.Dashboard.Home.UserHomeFragmentDirections
import com.example.code.Dashboard.Profile.UserProfileFragmentArgs
import com.example.code.Login.LoginFragmentDirections
import com.example.code.R
import com.example.code.data.source.model.Company
import com.example.code.databinding.FragmentUserCompanyBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class UserCompanyFragment : Fragment() {

    lateinit var binding:FragmentUserCompanyBinding
    val viewModel:UserCompanyViewModel by viewModels<UserCompanyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_company,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = UserCompanyFragmentArgs.fromBundle(requireArguments()).userId

        viewModel.getUserById(userId)

        var role = ""
        var companyId: String?= null
        var companyName: String?= null
        var announcement: String?= null

        viewModel.user.observe(viewLifecycleOwner, Observer{
            if(it.userRole != null) {
                role = it.userRole
                companyId = it.companyId
            }

            if(companyId != null) binding.btnAddUserCompany.visibility = View.GONE
            else binding.cvCompanyUserCompany.visibility = View.GONE


            companyId?.let{id ->
                viewModel.getCompany(id)
            }

            viewModel.company.observe(viewLifecycleOwner, Observer{company->
                companyName = company?.companyName
                announcement = company?.announcement
                binding.tvNameUserCompany.setText(company?.companyName)
                binding.tvAnnouncementUserCompany.setText(company?.announcement)
            })
        })

        viewModel.cek.observe(viewLifecycleOwner, Observer{
            binding.btnAddUserCompany.visibility = View.GONE
            binding.cvCompanyUserCompany.visibility = View.VISIBLE

            viewModel.getUserById(userId)

            val temp = viewModel.user

            if(temp.value?.userRole != null) {
                role = temp.value?.userRole.toString()
                companyId = temp.value?.companyId
            }

            if(companyId != null) binding.btnAddUserCompany.visibility = View.GONE
            else binding.cvCompanyUserCompany.visibility = View.GONE


            companyId?.let{id ->
                viewModel.getCompany(id)
            }

            viewModel.company.observe(viewLifecycleOwner, Observer{company->
                companyName = company?.companyName
                announcement = company?.announcement
                binding.tvNameUserCompany.setText(company?.companyName)
                binding.tvAnnouncementUserCompany.setText(company?.announcement)
            })
        })

        binding.btnAddUserCompany.setOnClickListener {
            if(role == "owner"){
                showInputDialogOwner() { companyName, companyKey ->
                    if(companyName == ""){
                        Toast.makeText(requireContext(), "Company Name cannot be empty!", Toast.LENGTH_SHORT).show()
                        return@showInputDialogOwner
                    }

                    if(companyKey == "" ){
                        Toast.makeText(requireContext(), "Company Name cannot be empty!", Toast.LENGTH_SHORT).show()
                        return@showInputDialogOwner
                    }

                    try {
                        viewModel.createCompany(companyName, userId, companyKey)
                        Toast.makeText(view.context, "Success", Toast.LENGTH_SHORT).show()
                    }catch (e: Exception){
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }

                }
            }else if(role == "employee"){
                showInputDialogEmployee() { secretKey ->

                    try {
                        viewModel.joinCompany(userId, secretKey)
                        viewModel.cekJoin()
                        Toast.makeText(view.context, "Success", Toast.LENGTH_SHORT).show()
                    }catch (e: Exception){
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.btnCompanyDashboardUserCompany.setOnClickListener {
            userId?.let { safeUserId ->
                val action = UserCompanyFragmentDirections.actionGlobalCompanyDashboardFragment(safeUserId)
                findNavController().navigate(action)
            }
        }
    }

    private fun showInputDialogOwner(onInputReceived: (String, String) -> Unit) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Enter company code")

//        INPUT COMPANY NAME
        val inputName = EditText(requireContext())
        inputName.hint = "Company name"
        inputName.background = requireContext().getDrawable(R.drawable.custom_edittext)

        val icon = requireContext().getDrawable(R.drawable.ic_baseline_person_24)
        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)

        inputName.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)

//        INPUT COMPANY SECRET KEY
        val inputKey = EditText(requireContext())
        inputKey.hint = "Company secret key"
        inputKey.background = requireContext().getDrawable(R.drawable.custom_edittext)

        val icon2 = requireContext().getDrawable(R.drawable.baseline_vpn_key_24)
        icon2?.setBounds(0, 0, icon2.intrinsicWidth, icon2.intrinsicHeight)

        inputKey.setCompoundDrawablesWithIntrinsicBounds(icon2, null, null, null)

//         Create Layout
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL

        val paddingDP = (16 * resources.displayMetrics.density).toInt()
        layout.setPadding(paddingDP, paddingDP, paddingDP, paddingDP) // Set padding for the layout

//         Add the EditTexts to the LinearLayout
        layout.addView(inputName)
        layout.addView(inputKey)

        builder.setView(layout)

        builder.setPositiveButton("OK") { dialog, which ->
            val inputName = inputName.text.toString().trim()
            val inputKey = inputKey.text.toString().trim()
            onInputReceived(inputName, inputKey) // Pass the entered text to the callback
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(requireContext().getDrawable(R.drawable.custom_popup))

        dialog.show()
    }

    private fun showInputDialogEmployee(onInputReceived: (String) -> Unit) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Enter company code")

        val inputField = EditText(requireContext())
        inputField.hint = "Company code"
        inputField.background = requireContext().getDrawable(R.drawable.custom_edittext)

        val icon = requireContext().getDrawable(R.drawable.baseline_vpn_key_24)
        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)

        inputField.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)

        builder.setView(inputField)

        builder.setPositiveButton("OK") { dialog, which ->
            val inputText = inputField.text.toString().trim()
            onInputReceived(inputText) // Pass the entered text to the callback
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()

        dialog.window?.setBackgroundDrawable(requireContext().getDrawable(R.drawable.custom_popup))

        dialog.show()
    }

}