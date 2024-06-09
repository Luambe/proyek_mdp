package com.example.code.Dashboard.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.code.R

class UserHomeFragment : Fragment() {
    private lateinit var tvWelcome: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_home, container, false)
        tvWelcome = view.findViewById(R.id.tvWelcome)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mendapatkan userId dari arguments menggunakan Bundle
        val userId = arguments?.getString("userId")
        println("User id di homefragment : $userId")

        // Menampilkan userId di TextView tvWelcome
        tvWelcome.text = "Welcome, User $userId"
    }
}
