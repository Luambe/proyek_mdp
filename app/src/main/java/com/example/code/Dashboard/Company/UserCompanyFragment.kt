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
import android.widget.Toast
import androidx.core.view.marginTop
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
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

        val companies = ArrayList<Company>()

        val companyAdapter = CompanyAdapter{
            val action = UserCompanyFragmentDirections.actionGlobalCompanyDashboardFragment()
            findNavController().navigate(action)
            viewModel.getCompanies()
        }
        binding.rvCompany.adapter = companyAdapter

        companyAdapter.submitList(ArrayList<Company>())

        val companiesObserver:Observer<List<Company>> = Observer{
            companyAdapter.submitList(it)
        }
        viewModel.companies.observe(viewLifecycleOwner, companiesObserver)

        viewModel.getCompanies()

        binding.rvCompany.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvCompany.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )

        binding.btnAdd.setOnClickListener {
            val action = UserCompanyFragmentDirections.actionUserCompanyFragmentToAddCompanyFragment(userId)
            findNavController().navigate(action)
            viewModel.getCompanies()

//            showInputDialog() { userInput ->
//                Toast.makeText(requireContext(), "You entered: $userInput", Toast.LENGTH_SHORT).show()
//            }
        }
    }
    private fun showInputDialog(onInputReceived: (String) -> Unit) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Enter company code")

        val inputField = EditText(requireContext())
        inputField.hint = "Company code"
        inputField.background = requireContext().getDrawable(R.drawable.custom_edittext)

        val icon = requireContext().getDrawable(R.drawable.baseline_vpn_key_24)
        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)

        inputField.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)

        builder.setView(inputField)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(requireContext().getDrawable(R.drawable.custom_popup))

        builder.setPositiveButton("OK") { dialog, which ->
            val inputText = inputField.text.toString().trim()
            onInputReceived(inputText) // Pass the entered text to the callback
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        dialog.show()
    }

}