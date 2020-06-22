package com.example.mycvapp

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.bottomNavigation

class MainActivity : AppCompatActivity() {

    private val baseFragments = setOf(R.id.personalDataFragment, R.id.workHistoryFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        (supportFragmentManager.findFragmentById(R.id.navigationHost) as NavHostFragment).run {
            val appBarConfiguration = AppBarConfiguration.Builder(baseFragments).build()
            NavigationUI.setupActionBarWithNavController(this@MainActivity, navController, appBarConfiguration)
            NavigationUI.setupWithNavController(this@MainActivity.bottomNavigation, navController)

            onBackPressedDispatcher.addCallback(this@MainActivity, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!navController.navigateUp()) {
                        finish()
                    }
                }
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return (Navigation.findNavController(this, R.id.navigationHost).navigateUp()
                || super.onSupportNavigateUp())
    }
}
