package com.tanveer.otptask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.tanveer.otptask.databinding.ActivityMainBinding
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
   private var navController: NavController? = null
    var appBarConfiguration : AppBarConfiguration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navController = findNavController(R.id.host)
        appBarConfiguration = navController?.graph?.let {
            AppBarConfiguration(it)
        }
        setupActionBarWithNavController(navController!!,appBarConfiguration!!)
        navController?.addOnDestinationChangedListener{ navController, destination, arguments ->
            when(destination.id){
                R.id.firstFragment -> supportActionBar?.title = resources.getString(R.string.login)
                R.id.secondFragment -> supportActionBar?.title = resources.getString(R.string.OTP)
                R.id.thirdFragment -> supportActionBar?.title = resources.getString(R.string.Password)
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()|| navController!!.popBackStack()
    }
}