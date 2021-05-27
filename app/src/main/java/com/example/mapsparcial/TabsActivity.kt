package com.example.mapsparcial

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mapsparcial.databinding.ActivityTabsBinding

class TabsActivity : AppCompatActivity() {

    lateinit var data: Coordenadas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityTabsBinding>(this, R.layout.activity_tabs)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.locationFragment, R.id.mapFragment))
        setSupportActionBar(binding.toolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navView.setupWithNavController(navController)

        //data = intent.getParcelableExtra<Coordenadas>("Coordenadas")!! // Get user data from arguments

    }
    public fun showFragment() {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, MapFragment(), null)
        transaction.commit()

    }
}