package com.hitesh.cureyaassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view);

        bottomNavigationView.selectedItemId = R.id.navigation_account
        setCurrentFragment(Profile())

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
                }
                R.id.navigation_allusers -> {
                    setCurrentFragment(AllUsers())
                }
                R.id.navigation_account -> {
                    setCurrentFragment(Profile())
                }
                R.id.navigation_search -> {
                    Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container2, fragment)
            commit()
        }

}