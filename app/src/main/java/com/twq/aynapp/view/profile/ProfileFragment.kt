package com.twq.aynapp.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.twq.aynapp.R
import com.twq.aynapp.model.*
import com.twq.aynapp.view.home.HomeAdapter
import com.twq.aynapp.view.home.HomeViewModel


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_profile, container, false)

//        val buttonProfileEdit = v.findViewById<ImageButton>(R.id.buttonProfileEditInfo)
//        buttonProfileEdit.setOnClickListener {
//
//        }
//        val buttonAdd = v.findViewById<Button>(R.id.floatingActionButton)


        val vm : ProfileViewModel by viewModels()
        val pRecyclerView = v.findViewById<RecyclerView>(R.id.pRecyclerView)
        pRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        vm.getProjectData().observe(this,{
            pRecyclerView.adapter = ProfileAdapter(it)
        })


        return v

    }

}