package com.example.starwarsapi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.starwarsapi.R
import com.example.starwarsapi.presentation.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btMainActivityFilm.setOnClickListener {
        }
        btMainActivitySpecies.setOnClickListener {
        }
        btMainActivityStarships.setOnClickListener {
            openStarshipActivity()
        }
        btMainActivityVehicles.setOnClickListener {
        }
        btMainActivityPeople.setOnClickListener {
            openPeopleActivity()
        }
        btMainActivityPlanet.setOnClickListener {
            openPlanetActivity()
        }

    }

    private fun openStarshipActivity() {
        val intent = Intent(this, ShowStarshipActivity::class.java)
        startActivity(intent)
    }

    private fun openPlanetActivity() {
        val intent = Intent(this, ShowPlanetActivity::class.java)
        startActivity(intent)
    }

    private fun openPeopleActivity() {
        val intent = Intent(this, ShowPeopleActivity::class.java)
        startActivity(intent)
    }

}
