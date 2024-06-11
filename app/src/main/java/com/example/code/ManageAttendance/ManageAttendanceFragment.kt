package com.example.code.ManageAttendance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.data.source.model.Attendance

class ManageAttendanceFragment : Fragment() {


    val viewModel: ManageAttendanceViewModel by viewModels<ManageAttendanceViewModel>()
    lateinit var btnBack : Button
    lateinit var rvAttendance: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_manage_attendance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBack = view.findViewById(R.id.btnBack)
        rvAttendance = view.findViewById(R.id.rvAttendance)

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val attendanceAdapter = AttendanceAdapter(viewModel) { userId ->
            viewModel.getUser(userId)
            viewModel.user.value?.userName ?: "Unknown User"
        }
        rvAttendance.adapter = attendanceAdapter

        attendanceAdapter.submitList(ArrayList<Attendance>())

        val attendanceObserver: Observer<List<Attendance>> = Observer {
            attendanceAdapter.submitList(it)
        }
        viewModel.attendances.observe(viewLifecycleOwner, attendanceObserver)

        viewModel.getAttendances()

        rvAttendance.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvAttendance.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )

    }

}