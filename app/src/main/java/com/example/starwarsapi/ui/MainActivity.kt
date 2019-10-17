package com.example.starwarsapi.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.starwarsapi.R
import com.example.starwarsapi.presentation.MainViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupNavigation()
    }

    private fun setupNavigation() {
        navView.setNavigationItemSelectedListener(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment),drawerLayout)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        drawerLayout.closeDrawers()
        when (item.itemId) {
            R.id.people_item -> navController.navigate(R.id.action_baseFragment_to_showPeopleFragment)
            R.id.specie_item -> navController.navigate(R.id.action_baseFragment_to_showSpecieFragment)
            R.id.planet_item -> navController.navigate(R.id.action_baseFragment_to_showPlanetFragment)
            R.id.vehicle_item -> navController.navigate(R.id.action_baseFragment_to_showVehicleFragment)
            R.id.starship_item -> navController.navigate(R.id.action_baseFragment_to_showStarshipFragment)
            R.id.film_item -> navController.navigate(R.id.action_baseFragment_to_showFilmFragment)
            else -> Toast.makeText(this, "Menu default", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}
