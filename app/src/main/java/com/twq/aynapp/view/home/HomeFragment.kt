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
        // Inflate the layout for this fragment


       val v = inflater.inflate(R.layout.fragment_home, container, false)

//
//        var cRecyclerView = v.findViewById<RecyclerView>(R.id.hRecyclerView)
//        cRecyclerView.layoutManager = LinearLayoutManager(context)
//        cRecyclerView.adapter = CarAdapter(carList)
//        return v
//


//        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = MovieAdapter(movieList)

        val vm : HomeViewModel by viewModels()
        var cRecyclerView = v.findViewById<RecyclerView>(R.id.hRecyclerView)
        cRecyclerView.layoutManager = LinearLayoutManager(context)
        vm.getProjectData().observe(this,{
            cRecyclerView.adapter = HomeAdapter(it)

        })

        return v
    }


}