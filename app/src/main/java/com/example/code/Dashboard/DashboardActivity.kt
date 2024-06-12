package com.example.code.Dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.example.code.Dashboard.Home.UserHomeFragmentArgs
import com.example.code.Dashboard.Home.UserHomeFragmentDirections
import com.example.code.MainActivity
import com.example.code.R
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var menuButton: ImageButton
    var userId:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Mendapatkan userId dari Intent
        userId = intent.getStringExtra("userId")
        println("User id di dashboard activity habis di intent : ${userId}")

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
                R.id.itemCompany -> {
                    userId?.let { safeUserId ->
                        val action = UserHomeFragmentDirections.actionGlobalUserCompanyFragment(safeUserId)
                        findNavController(R.id.navHost_nav_dashboard).navigate(action)
                        drawerLayout.close()
                    }
                }

                R.id.itemProfile -> {
                    userId?.let { safeUserId ->
                        val action = UserHomeFragmentDirections.actionGlobalUserProfileFragment(safeUserId)
                        findNavController(R.id.navHost_nav_dashboard).navigate(action)
                        drawerLayout.close()
                    }
                }

                R.id.itemLogout -> {
//                    showLogoutConfirmationDialog()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout Confirmation")
        builder.setMessage("Are you sure you want to logout?")

        builder.setPositiveButton("Logout") { dialog, which ->
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}