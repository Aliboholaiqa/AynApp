package com.twq.aynapp.view.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.model.*
import com.twq.aynapp.view.home.HomeActivity
import com.twq.aynapp.view.login.LoginActivity
import java.util.*


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        val vm : ProfileViewModel by viewModels()
        val auth = Firebase.auth
        val username = v.findViewById<TextView>(R.id.textViewProfileUsername)
        val bio = v.findViewById<TextView>(R.id.textViewProfileBio)
        val avatar = v.findViewById<ImageView>(R.id.imageViewProfileAvatar)
        val header = v.findViewById<ImageView>(R.id.imageViewHeader)

        vm.getUserData().observe(this,{
            username.text = it.username
            bio.text = it.bio
            Picasso.get().load(it.avatar).into(avatar)
            Picasso.get().load(it.header).into(header)
        })

        val buttonProfileEdit = v.findViewById<ImageButton>(R.id.buttonProfileEditInfo)
        val buttonAdd = v.findViewById<Button>(R.id.floatingActionButton)

        // spinner with edit profile and sign out
        buttonProfileEdit.setOnClickListener {
            val profileMenu= PopupMenu(context,buttonProfileEdit)
            profileMenu.menuInflater.inflate(R.menu.profile_menu,profileMenu.menu)
            profileMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {

                    R.id.editProfileItem -> {val intent = Intent (context, ProfileEditInfoActivity::class.java)
                        startActivity(intent) }

                    R.id.signOutItem -> { auth.signOut()
                        val intent = Intent (context, LoginActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    }

                    R.id.theme -> {}

                    R.id.nightModeItem -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                    R.id.lightModeItem -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                    R.id.changeLanguage -> {}

                    R.id.English -> setLocale("en")

                    R.id.Arabic -> setLocale("ar")

                    R.id.help -> {
                        val intent = Intent(Intent.ACTION_SENDTO)
                        intent.data=Uri.parse("mailto:")
                        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("support@aynapp.com"))
                        intent.putExtra(Intent.EXTRA_SUBJECT,"Customer Service")
                        startActivity(intent)
                    }
                }
                true
            }
            )
            profileMenu.show()
        }

        buttonAdd.setOnClickListener {
            val intent = Intent (context, ProfileAddProjectActivity::class.java)
            startActivity(intent)
        }

        val pRecyclerView = v.findViewById<RecyclerView>(R.id.pRecyclerView)
        pRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        vm.getUserProject(auth.currentUser?.uid.toString()).observe(this,{
                pRecyclerView.adapter = ProfileAdapter(it)
        })

        return v
    }

    fun setLocale(localeName: String?){
        val locale = Locale(localeName)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.setLocale(locale)
        res.updateConfiguration(conf, dm)
        val i = Intent(context, HomeActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(i)
    }
}