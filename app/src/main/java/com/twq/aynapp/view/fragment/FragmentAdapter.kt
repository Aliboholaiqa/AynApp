package com.twq.aynapp.view.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.twq.aynapp.view.home.HomeFragment
import com.twq.aynapp.view.profile.ProfileFragment
import com.twq.aynapp.view.saved.SavedFragment

class FragmentAdapter(activity:FragmentActivity):FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        // if (position==0){ return HomeFragment() }
        when (position){
            0 -> return HomeFragment()
            1 -> return SavedFragment()
            2 -> return ProfileFragment()
        }
        return HomeFragment()
    }
}