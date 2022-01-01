package com.twq.aynapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityHomeBinding
import com.twq.aynapp.view.profile.ProfileFragment
import com.twq.aynapp.view.saved.SavedFragment
import com.twq.aynapp.view.search.SearchFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Binding
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Navigation view listener
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {  item ->
            when(item.itemId){
                R.id.navHome ->{
                    //HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,HomeFragment()).commit()
                    true
                }

                R.id.navSearch ->{
                    //SearchFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, SearchFragment()).commit()
                    true
                }

                R.id.navSaved ->{
                    //SavedFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,SavedFragment()).commit()
                    true
                }

                R.id.navProfile ->{
                    //ProfileFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,ProfileFragment()).commit()
                    true
                }
                else -> {false}
            }
        }
    }
}
