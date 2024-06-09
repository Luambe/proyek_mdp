package com.example.code.ManageAttendance

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.code.R

class ManageAttendanceFragment : Fragment() {

    companion object {
        fun newInstance() = ManageAttendanceFragment()
    }

    private lateinit var viewModel: ManageAttendanceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_manage_attendance, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ManageAttendanceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}