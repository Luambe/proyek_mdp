package com.example.code.TaskMenu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.code.CompanyDashboard.CompanyDashboardFragmentDirections
import com.example.code.Dashboard.Profile.UserProfileFragmentArgs
import com.example.code.Dashboard.Profile.UserProfileViewModel
import com.example.code.R

class TaskMenuFragment : Fragment() {

    companion object {
        fun newInstance() = TaskMenuFragment()
    }

    val viewModel: TaskMenuViewModel by viewModels<TaskMenuViewModel>()
    lateinit var tvCompany: TextView
    lateinit var btnBack: Button
    lateinit var cardTaskList: CardView
    lateinit var cardAddTask: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCompany = view.findViewById(R.id.tv_company_taskMenu)
        btnBack = view.findViewById(R.id.btn_back_taskMenu)
        cardTaskList = view.findViewById(R.id.card_task_list)
        cardAddTask = view.findViewById(R.id.card_add_task)

        val userId = TaskMenuFragmentArgs.fromBundle(requireArguments()).userId

        println("taskmenu: userid:")
        println(userId)

        viewModel.getUserById(userId)

        var companyId: String?= null

        viewModel.user.observe(viewLifecycleOwner, Observer{
            if(it.userRole != null) {
                companyId = it.companyId
            }

            companyId?.let{id ->
                viewModel.getCompany(id)
            }

            viewModel.company.observe(viewLifecycleOwner, Observer{company->
                tvCompany.setText(company?.companyName)
            })
        })

        cardAddTask.setOnClickListener{
            userId?.let { safeUserId ->
                val action =
                    TaskMenuFragmentDirections.actionGlobalTaskListFragment(safeUserId)
                findNavController().navigate(action)
            }
        }

        cardTaskList.setOnClickListener{
            userId?.let { safeUserId ->
                val action =
                    TaskMenuFragmentDirections.actionGlobalAddTaskFragment(safeUserId)
                findNavController().navigate(action)
            }
        }

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}