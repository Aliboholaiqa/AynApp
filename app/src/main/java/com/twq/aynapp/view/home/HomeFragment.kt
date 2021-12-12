package com.twq.aynapp.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twq.aynapp.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val vm : HomeViewModel by viewModels()
        var hRecyclerView = v.findViewById<RecyclerView>(R.id.hRecyclerView)
        hRecyclerView.layoutManager = LinearLayoutManager(context)
        vm.getProjectData().observe(this,{
            hRecyclerView.adapter = HomeAdapter(it)
        })

        return v
    }


}