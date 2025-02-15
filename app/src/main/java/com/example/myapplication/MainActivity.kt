package com.example.myapplication

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var frameLayout: FrameLayout
    lateinit var bottomBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frameLayout = findViewById(R.id.mainFrameLayout)
        bottomBar = findViewById(R.id.mainBottomBar)

        bottomBar.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
            true
        })
        bottomBar.selectedItemId = R.id.home

        lifecycleScope.launch {
            val res = SupabaseClient.getShoesByString("p")
            onRes(res)
        }
    }

    private fun loadFragment(frag: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrameLayout, frag)
        transaction.commit()
    }

    private fun onRes(res: List<Shoe>) {
        println(res)
    }


}