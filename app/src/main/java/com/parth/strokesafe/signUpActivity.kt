package com.parth.strokesafe

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.view.View.inflate
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.parth.strokesafe.databinding.ActivitySignUpBinding
import java.util.*


class signUpActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: DatabaseReference
    private lateinit var storage: FirebaseStorage
    private lateinit var fd: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        fd = FirebaseDatabase.getInstance()
        db = fd.getReference("Users")
        storage= FirebaseStorage.getInstance()

        binding.toSignIn.setOnClickListener{
            val intent =Intent(this,signInActivity::class.java)
            startActivity(intent)
        }


        var passwordVisible = false
        binding.SignUpPassword.setOnTouchListener { v, event ->
            val Right = 2
            if (event.getAction() === MotionEvent.ACTION_UP) {
                if (event.getRawX() >= binding.SignUpPassword.getRight() - binding.SignUpPassword.getCompoundDrawables()
                        .get(Right).getBounds().width()
                ) {
                    val selection: Int = binding.SignUpPassword.getSelectionEnd()
                    //Handles Multiple option popups
                    if (passwordVisible) {
                        //set drawable image here
                        binding.SignUpPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.visibility_off,
                            0
                        )
                        //for hide password
                        binding.SignUpPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                        passwordVisible = false
                    } else {
                        //set drawable image here
                        binding.SignUpPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.visibility,
                            0
                        )
                        //for show password
                        binding.SignUpPassword.setTransformationMethod(
                            HideReturnsTransformationMethod.getInstance())
                        passwordVisible = true
                    }
                    binding.SignUpPassword.setLongClickable(false) //Handles Multiple option popups
                    binding.SignUpPassword.setSelection(selection)
                    return@setOnTouchListener true
                }
            }
            false
        }


        binding.createAccount.setOnClickListener{

            if(binding.SignUpName.text.toString().trim().isNotEmpty()&&binding.SignUpEmail.text.toString().trim().isNotEmpty()&& binding.SignUpPassword.text.toString().trim().isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(binding.SignUpEmail.text.toString().trim()).matches()){
                if(binding.SignUpPassword.text.toString().trim().length>7){
                    firebaseAuth.createUserWithEmailAndPassword(binding.SignUpEmail.text.toString().trim(),binding.SignUpPassword.text.toString()).addOnCompleteListener{ it ->
                        if(it.isSuccessful){
                            val u = firebaseAuth.currentUser
                            val uid = firebaseAuth.currentUser?.uid.toString()

                            val user=User(binding.SignUpName.text.toString().trim(),binding.SignUpEmail.text.toString().trim(),binding.SignUpPhone.text.toString().trim(),uid)

                            if (u != null) {
                                db.child(u.uid).setValue(user).addOnCompleteListener{
                                    if(it.isSuccessful){
                                        u.sendEmailVerification()
                                        val intent=Intent(this,MainActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                }
                            }



                        }
                    }
                }
            }
        }
    }
}