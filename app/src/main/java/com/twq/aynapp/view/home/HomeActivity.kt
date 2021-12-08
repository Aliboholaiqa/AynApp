package com.twq.aynapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityHomeBinding
import com.twq.aynapp.utility.DepthPageTransformer
import com.twq.aynapp.view.fragment.FragmentAdapter

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Binding
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Attaching the fragments with Page transformation animation
        binding.viewPager2.adapter = FragmentAdapter(this)
        binding.viewPager2.setPageTransformer(DepthPageTransformer())

        //Including icons inside the tab layout
        val icons = arrayOf(R.drawable.ic_home,R.drawable.ic_bookmark,R.drawable.ic_add_user)
        TabLayoutMediator(binding.tabLayout,binding.viewPager2){tab, position ->
            tab.setIcon(icons[position])
        }.attach()
    }
}
