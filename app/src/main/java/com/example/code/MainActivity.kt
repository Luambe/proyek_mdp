package com.example.code

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.code.Dashboard.DashboardActivity
import com.example.code.Login.NavigationListener

class MainActivity : AppCompatActivity(), NavigationListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //change 1
    }

    override fun navigateToActivityDashboard(userId: String) {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }
}