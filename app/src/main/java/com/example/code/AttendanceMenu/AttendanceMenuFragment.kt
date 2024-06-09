package com.example.code.AttendanceMenu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.code.R

class AttendanceMenuFragment : Fragment() {

    companion object {
        fun newInstance() = AttendanceMenuFragment()
    }

    private lateinit var viewModel: AttendanceMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attendance_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AttendanceMenuViewModel::class.java)
        // TODO: Use the ViewModel
    }

}