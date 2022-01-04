package com.twq.aynapp.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.model.User
import com.twq.aynapp.adapter.SearchAdapter

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_search, container, false)
        //val db = FirebaseFirestore.getInstance()
        val vm: SearchViewModel by viewModels()
        var searchView = v.findViewById<SearchView>(R.id.searchView)
        val sRecyclerView = v.findViewById<RecyclerView>(R.id.searchRecyclerView)
        sRecyclerView.layoutManager = LinearLayoutManager(context)
        vm.getAllUsers().observe(this,{
            sRecyclerView.adapter = SearchAdapter(it)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    val newData = it.filter { user: User -> user.username?.toLowerCase()!!.contains(p0!!)  } as MutableList<User>
                    sRecyclerView.adapter = SearchAdapter(newData)
                    return true
                }
            })
        })


        return v
    }


}