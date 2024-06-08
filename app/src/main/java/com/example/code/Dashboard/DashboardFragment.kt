package com.example.code.Dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.code.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashboardFragment : Fragment() {

    lateinit var btnHamburger: ImageButton
    lateinit var btnCloseHamburger: ImageButton
    lateinit var expandableMenu: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        btnHamburger = view.findViewById(R.id.navMenuButton)
        btnCloseHamburger = view.findViewById(R.id.navMenuCloseButton)
        expandableMenu = view.findViewById(R.id.expandable_menu)

        btnHamburger.setOnClickListener {
            toggleExpandableMenu()
        }

        btnCloseHamburger.setOnClickListener{
            toggleExpandableMenu()
        }

        return view
    }

    private fun toggleExpandableMenu() {
        if (expandableMenu.visibility == View.GONE) {
            expandableMenu.visibility = View.VISIBLE
            expandableMenu.layoutParams.width = (300 * resources.displayMetrics.density).toInt()
            btnHamburger.isClickable = false
            // Optional animation for expanding the menu
        } else {
            expandableMenu.visibility = View.GONE
            expandableMenu.layoutParams.width = 0
            btnHamburger.isClickable = true
            // Optional animation for collapsing the menu
        }
    }

    fun closeExpandableMenu() {
        return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}