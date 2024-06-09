package com.example.code.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.example.code.R
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var menuButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navView)

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)

        // Menu button setup
        menuButton = findViewById(R.id.menu_button)
        menuButton.setImageResource(R.drawable.baseline_dehaze_24)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        menuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemHome -> {
                    findNavController(R.id.navHost_nav_dashboard).
                    navigate(R.id.action_global_userHomeFragment)
                    drawerLayout.close()
                }

                R.id.itemCompany -> {
                    findNavController(R.id.navHost_nav_dashboard).
                    navigate(R.id.action_global_userCompanyFragment)
                    drawerLayout.close()
                }

                R.id.itemTask -> {
                    findNavController(R.id.navHost_nav_dashboard).
                    navigate(R.id.action_global_userTaskFragment)
                    drawerLayout.close()
                }

                R.id.itemProfile -> {
                    findNavController(R.id.navHost_nav_dashboard).
                    navigate(R.id.action_global_userProfileFragment)
                    drawerLayout.close()
                }

                R.id.itemLogout -> {
                    finish()
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}