package com.twq.aynapp.view.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twq.aynapp.R
import com.twq.aynapp.model.Project
import com.twq.aynapp.view.home.HomeViewModel


class SavedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_saved, container, false)
        val vm: HomeViewModel by viewModels()

 //       val saved = activity!!.getIntent().getSerializableExtra("saved") as Project
        //vm.saveProject(saved.projectTitle,saved.description,saved.image!!,saved.createdAt)
//
//        val sRecyclerView = v.findViewById<RecyclerView>(R.id.sRecyclerView)
//        sRecyclerView.layoutManager = LinearLayoutManager(context)
//        sRecyclerView.adapter = SavedAdapter(saved)



        return v
    }
}