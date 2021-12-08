package com.twq.aynapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityHomeBinding
import com.twq.aynapp.view.profile.ProfileFragment
import com.twq.aynapp.view.saved.SavedFragment

class HomeActivity : AppCompatActivity() {
   // lateinit var recyclerView: RecyclerView
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
