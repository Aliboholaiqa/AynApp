package com.twq.aynapp.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.twq.aynapp.R
import com.twq.aynapp.model.Project

class HomeFragment : Fragment() {
    val auth = Firebase.auth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val vm : HomeViewModel by viewModels()
        val hRecyclerView = v.findViewById<RecyclerView>(R.id.hRecyclerView)
        hRecyclerView.layoutManager = LinearLayoutManager(context)
//        vm.getProjectData(auth.uid.toString()).observe(this,{
//            hRecyclerView.adapter = HomeAdapter(it)
//        })


        return v
    }


}