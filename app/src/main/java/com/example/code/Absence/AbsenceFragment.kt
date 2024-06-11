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
import com.example.code.R
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

class AbsenceFragment : Fragment() {

    private val viewModel: AbsenceViewModel by viewModels()
    lateinit var tvTime : TextView
    lateinit var btn_check : Button

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

        viewModel.currentTime.observe(this, Observer { currentTime ->
            tvTime.text = "$currentTime"
        })

        btn_check.setOnClickListener {
            val currentTime = LocalTime.parse(viewModel.currentTime.value)
            val eightAM = LocalTime.of(8, 0, 0)

            if (currentTime.isAfter(eightAM)) {
                println("Waktu sekarang lebih besar dari jam 8 pagi.")
            } else {
                println("Waktu sekarang kurang dari atau sama dengan jam 8 pagi.")
            }
        }

    }

}