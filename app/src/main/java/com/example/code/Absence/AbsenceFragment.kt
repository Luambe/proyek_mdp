package com.example.code.Absence

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.code.AttendanceMenu.AttendanceMenuFragmentArgs
import com.example.code.R
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

class AbsenceFragment : Fragment() {

    private val viewModel: AbsenceViewModel by viewModels()
    lateinit var tvTime : TextView
    lateinit var tvStatus : TextView
    lateinit var btn_check : Button
    lateinit var btn_back_attendance_menu : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_absence, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTime = view.findViewById(R.id.tvTime)
        btn_check = view.findViewById(R.id.btn_check)
        btn_back_attendance_menu = view.findViewById(R.id.btn_back_attendance_menu)
        tvStatus = view.findViewById(R.id.tvStatus)

        val userId = AbsenceFragmentArgs.fromBundle(requireArguments()).userId

        viewModel.currentTime.observe(this, Observer { currentTime ->
            tvTime.text = "$currentTime"
        })

        println("Debug 1")
        viewModel.getAttendanceByUserId(userId)
        viewModel.status.observe(viewLifecycleOwner, Observer {status ->
            if (status == "sudah") {
                btn_check.text = "Checked"
                btn_check.isEnabled = false
                viewModel.succes.observe(viewLifecycleOwner, Observer {
                    println("masuk")
                    tvStatus.text = if (it == "1") "Late" else "On Time"
                    println("masuk2")
                })
            }
        })


        println("Debug 2")

        btn_check.setOnClickListener {
            val currentTime = LocalTime.parse(viewModel.currentTime.value)
            val eightAM = LocalTime.of(8, 0, 0)

            if (currentTime.isAfter(eightAM)) {
                viewModel.createAttendance(userId, "1")
                tvStatus.text = "Late"
                btn_check.text = "Checked"
            } else {
                viewModel.createAttendance(userId, "0")
                tvStatus.text = "On Time"
                btn_check.text = "Checked"
            }
        }

        btn_back_attendance_menu.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}