package com.twq.aynapp.view.saved

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twq.aynapp.R
import com.twq.aynapp.model.Project
import com.twq.aynapp.view.home.HomeAdapter


class SavedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_saved, container, false)


//        var saved = activity!!.getIntent().getSerializableExtra("saved") as Project
//
//        val sRecyclerView = v.findViewById<RecyclerView>(R.id.sRecyclerView)
//        sRecyclerView.layoutManager = LinearLayoutManager(context)
//        sRecyclerView.adapter = SavedFragment(saved)



        return v
    }
}