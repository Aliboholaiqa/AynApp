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

//        var movieList = mutableListOf<Movie>(
//            Movie(
//                "The Shawshank ",
//                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
//                1994,
//                "https://m.media-amazon.com/images/M/MV5BMzJkNjNiOWUtYzNmOC00MGFjLWE5N2EtMjdkYjEwMzQ4NzBhXkEyXkFqcGdeQXVyMTAyOTE2ODg0._V1_UX100_CR0,0,100,100_AL_.jpg"
//            ),
//            Movie(
//                "The Godfather",
//                "The aging patriarch of an organized crime dynasty in postwar New York City transfers control of his clandestine empire to his reluctant youngest son.",
//                1972,
//                "https://m.media-amazon.com/images/M/MV5BOGU1YjI0NmEtN2JiZi00YWRjLWJkMjctODFlMjgyYTA1MmJiXkEyXkFqcGdeQXVyMDc2NTEzMw@@._V1_UY100_CR17,0,100,100_AL_.jpg"
//            ),
//            Movie(
//                "The Dark Knight",
//                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
//                1998,
//                "https://m.media-amazon.com/images/M/MV5BMjA2ODk2ODAwMV5BMl5BanBnXkFtZTcwNzYzMTk2Mw@@._V1_UX100_CR0,0,100,100_AL_.jpg"
//            ),
//            Movie(
//                "The Shawshank Redemption",
//                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
//                1994,
//                "https://m.media-amazon.com/images/M/MV5BMzJkNjNiOWUtYzNmOC00MGFjLWE5N2EtMjdkYjEwMzQ4NzBhXkEyXkFqcGdeQXVyMTAyOTE2ODg0._V1_UX100_CR0,0,100,100_AL_.jpg"
//            ),
//            Movie(
//                "The Godfather",
//                "The aging patriarch of an organized crime dynasty in postwar New York City transfers control of his clandestine empire to his reluctant youngest son.",
//                1972,
//                "https://m.media-amazon.com/images/M/MV5BOGU1YjI0NmEtN2JiZi00YWRjLWJkMjctODFlMjgyYTA1MmJiXkEyXkFqcGdeQXVyMDc2NTEzMw@@._V1_UY100_CR17,0,100,100_AL_.jpg"
//            ),
//            Movie(
//                "The Dark Knight",
//                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
//                1998,
//                "https://m.media-amazon.com/images/M/MV5BMjA2ODk2ODAwMV5BMl5BanBnXkFtZTcwNzYzMTk2Mw@@._V1_UX100_CR0,0,100,100_AL_.jpg"
//            ),
//            Movie(
//                "The Shawshank Redemption",
//                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
//                1994,
//                "https://m.media-amazon.com/images/M/MV5BMzJkNjNiOWUtYzNmOC00MGFjLWE5N2EtMjdkYjEwMzQ4NzBhXkEyXkFqcGdeQXVyMTAyOTE2ODg0._V1_UX100_CR0,0,100,100_AL_.jpg"
//            ),
//            Movie(
//                "The Godfather",
//                "The aging patriarch of an organized crime dynasty in postwar New York City transfers control of his clandestine empire to his reluctant youngest son.",
//                1972,
//                "https://m.media-amazon.com/images/M/MV5BOGU1YjI0NmEtN2JiZi00YWRjLWJkMjctODFlMjgyYTA1MmJiXkEyXkFqcGdeQXVyMDc2NTEzMw@@._V1_UY100_CR17,0,100,100_AL_.jpg"
//            ),
//            Movie(
//                "The Dark Knight",
//                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
//                1998,
//                "https://m.media-amazon.com/images/M/MV5BMjA2ODk2ODAwMV5BMl5BanBnXkFtZTcwNzYzMTk2Mw@@._V1_UX100_CR0,0,100,100_AL_.jpg"
//            ),
//            Movie(
//                "The Shawshank Redemption",
//                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
//                1994,
//                "https://m.media-amazon.com/images/M/MV5BMzJkNjNiOWUtYzNmOC00MGFjLWE5N2EtMjdkYjEwMzQ4NzBhXkEyXkFqcGdeQXVyMTAyOTE2ODg0._V1_UX100_CR0,0,100,100_AL_.jpg"
//            ),
//            Movie(
//                "The Godfather",
//                "The aging patriarch of an organized crime dynasty in postwar New York City transfers control of his clandestine empire to his reluctant youngest son.",
//                1972,
//                "https://m.media-amazon.com/images/M/MV5BOGU1YjI0NmEtN2JiZi00YWRjLWJkMjctODFlMjgyYTA1MmJiXkEyXkFqcGdeQXVyMDc2NTEzMw@@._V1_UY100_CR17,0,100,100_AL_.jpg"
//            ),
//            Movie(
//                "The Dark Knight",
//                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
//                1998,
//                "https://m.media-amazon.com/images/M/MV5BMjA2ODk2ODAwMV5BMl5BanBnXkFtZTcwNzYzMTk2Mw@@._V1_UX100_CR0,0,100,100_AL_.jpg"
//            ),
//        )
//        val pRecyclerView = v.findViewById<RecyclerView>(R.id.pRecyclerView)
//        pRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
//        pRecyclerView.adapter = ProfileAdapter(movieList)

        var carList = mutableListOf<Car>(
            Car("BMW",getString(R.string.bmw),300000.0f,2020),
            Car("Jaguar",getString(R.string.jaguarimg),720000.0f,2020),
            Car("Tesla",getString(R.string.Ximg),270000.0f,2020),
            Car("Aston Martin",getString(R.string.Aston),2000000.0f,2020),
            Car("Cadillac",getString(R.string.cady),166000.0f,1966),

            )

        var cRecyclerView = v.findViewById<RecyclerView>(R.id.pRecyclerView)
        cRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        cRecyclerView.adapter = CarAdapter(carList)

        return v

    }

}