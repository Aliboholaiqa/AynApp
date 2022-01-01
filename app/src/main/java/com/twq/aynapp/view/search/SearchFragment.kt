package com.twq.aynapp.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.view.adapter.SearchAdapter

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_search, container, false)
        val username = v.findViewById<TextView>(R.id.textViewSearchUsername)
        val avatar = v.findViewById<ImageView>(R.id.imageViewSearchAvatar)
        val vm: SearchViewModel by viewModels()
// val hRecyclerView = v.findViewById<RecyclerView>(R.id.hRecyclerView)
//        hRecyclerView.layoutManager = LinearLayoutManager(context)
////        vm.getProjectData(auth.uid.toString()).observe(this,{
////            hRecyclerView.adapter = HomeAdapter(it)
////        })
         val sRecyclerView = v.findViewById<RecyclerView>(R.id.searchRecyclerView)
        sRecyclerView.layoutManager = LinearLayoutManager(context)
        vm.getAllUsers().observe(this,{
            sRecyclerView.adapter = SearchAdapter(it)
        })


        return v
    }


}