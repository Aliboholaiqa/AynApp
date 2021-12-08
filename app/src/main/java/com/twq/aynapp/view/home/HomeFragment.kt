package com.twq.aynapp.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twq.aynapp.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)

//        var v = inflater.inflate(R.layout.fragment_home, container, false)
//        var mRecyclerView = v.findViewById<RecyclerView>(R.id.hRecyclerView)
//        mRecyclerView.layoutManager = LinearLayoutManager(v.context)
//        mRecyclerView.adapter = PostAdapter()
//        return v

    }


}