package com.twq.aynapp.view.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityRegistrationBinding
import com.twq.aynapp.view.home.HomeActivity
import com.twq.aynapp.view.home.HomeFragment
import com.twq.aynapp.view.login.LoginActivity

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vm: RegistrationViewModel by viewModels()
        binding.buttonRegistrationRegister.setOnClickListener {
            vm.register(binding.editTextNameRegister.text.toString(),
                binding.editTextEmailRegister.text.toString(),
                binding.editTextPasswordRegister.text.toString()).observe(this, {
                if (it) {
                    startActivity(Intent(this, HomeActivity::class.java))
                }
            })
        }

        binding.buttonLoginRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}