package com.twq.aynapp.view.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.twq.aynapp.databinding.ActivityRegistrationBinding
import com.twq.aynapp.utility.EmailValidator
import com.twq.aynapp.utility.SharedPreferenceHelper
import com.twq.aynapp.view.home.HomeActivity
import com.twq.aynapp.view.login.LoginActivity
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var binding : ActivityRegistrationBinding
    private var mEmailValidator: EmailValidator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        binding.buttonRegistrationRegister.setOnClickListener {
            val vm : RegistrationViewModel by viewModels()
            vm.register(binding.editTextNameRegister.text.toString(),
                binding.editTextEmailRegister.text.toString(),
                binding.editTextPasswordRegister.text.toString()).observe(this,{
                if(it){
                    Toast.makeText(this, "Unable to register", Toast.LENGTH_SHORT).show()
                }else{
                    SharedPreferenceHelper.saveToken(this,auth.uid.toString())
                    startActivity(Intent(this, HomeActivity::class.java))
                }
            })
        }

        binding.buttonLoginRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun onRegisterClick() {
        // Don't save if the fields do not validate.
        if (!mEmailValidator?.isValid!!) {
            binding.editTextEmailRegister.error = "Invalid email"
            Log.w("Doc", "Not saving personal information: Invalid email")
            return
        }
        SharedPreferenceHelper.saveToken(this,auth.uid.toString())
        Log.i("Doc", "Personal information saved")

    }

}
