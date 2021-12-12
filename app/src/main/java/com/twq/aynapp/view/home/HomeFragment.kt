package com.twq.aynapp.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twq.aynapp.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


       val v = inflater.inflate(R.layout.fragment_home, container, false)
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
//        var cRecyclerView = v.findViewById<RecyclerView>(R.id.hRecyclerView)
//        cRecyclerView.layoutManager = LinearLayoutManager(context)
//        cRecyclerView.adapter = CarAdapter(carList)
//        return v
//


//        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = MovieAdapter(movieList)

        return v
    }


}