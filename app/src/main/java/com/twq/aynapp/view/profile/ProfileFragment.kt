package com.twq.aynapp.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.twq.aynapp.R
import com.twq.aynapp.model.*


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_profile, container, false)

        val buttonProfileEdit = v.findViewById<ImageButton>(R.id.buttonProfileEditInfo)
        buttonProfileEdit.setOnClickListener {

        }
        val buttonAdd = v.findViewById<Button>(R.id.floatingActionButton)


//        val pRecyclerView = v.findViewById<RecyclerView>(R.id.pRecyclerView)
//        pRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
//        pRecyclerView.adapter = ProfileAdapter(movieList)
//
//        var carList = mutableListOf<Car>(
//            Car("BMW",getString(R.string.bmw),300000.0f,2020),
//            Car("Jaguar",getString(R.string.jaguarimg),720000.0f,2020),
//            Car("Tesla",getString(R.string.Ximg),270000.0f,2020),
//            Car("Aston Martin",getString(R.string.Aston),2000000.0f,2020),
//            Car("Cadillac",getString(R.string.cady),166000.0f,1966),
//
//            )
//
//        var cRecyclerView = v.findViewById<RecyclerView>(R.id.pRecyclerView)
//        cRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
//        cRecyclerView.adapter = CarAdapter(carList)

        return v

    }

}