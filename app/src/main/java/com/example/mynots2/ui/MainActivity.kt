package com.example.mynots2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.mynots2.R
import com.example.mynots2.data.local.Pref
import com.example.mynots2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var pref: Pref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = Pref(this)


        val navHost: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        val navGraph = navHost.navController.navInflater.inflate(R.navigation.nav_graph)

        navGraph.setStartDestination(
            if (pref.isUserSeen() && pref.isUserAuth()) {
                R.id.MainFragment
            }else if(pref.isUserSeen() && !pref.isUserAuth()){
                R.id.AuthFragment
            } else {
                R.id.OnBoardFragment

            }
        )
        navHost.navController.graph = navGraph

    }


}