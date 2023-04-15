package com.parth.strokesafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        auth = FirebaseAuth.getInstance(); //initialize Firebase Auth
        val currentUser: FirebaseUser? = auth.getCurrentUser() //Get the current user

        Handler().postDelayed({
        if (currentUser == null) {
        val intent=Intent(this,signInActivity::class.java)
        startActivity(intent)
        finish()//If the user has not logged in, send them to On-Boarding Activity
        }
        else {
            //If user was logged in last time

                val loginIntent: Intent
                loginIntent = Intent(this,  MainActivity::class.java) //If the user email is verified
                startActivity(loginIntent)
                finish()

        }
        }, 2000)
    }
}