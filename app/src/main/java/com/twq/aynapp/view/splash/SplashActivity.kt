package com.twq.aynapp.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.twq.aynapp.R
import com.twq.aynapp.view.home.HomeActivity
import com.twq.aynapp.view.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            checkState()
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
            finish()
        }, 4000) // 3000 is the delayed time in milliseconds.
    }

    var auth = Firebase.auth
    fun checkState(){
        val user = auth.currentUser
        if (user == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}