package com.twq.aynapp.view.profile

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityProfileEditInfoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileEditInfoActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityProfileEditInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.buttonEditProfile.setOnClickListener {
            updateProfile()
        }
    }


    fun updateProfile(){
        auth.currentUser?.let { user ->
            val username = binding.editTextEditProfileName.text.toString()
            var email = binding.editTextEditProfileEmail.text.toString()
            val image = Uri.parse(R.drawable.ic_launcher_ayn_background.toString())
            val profileUpdate = UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .setPhotoUri(image)
                .build()
            user.updateProfile(profileUpdate)
        }
    }
}