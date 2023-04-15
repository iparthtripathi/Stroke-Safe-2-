package com.parth.strokesafe
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.parth.strokesafe.databinding.ActivitySignInBinding

class signInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var auth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var user: FirebaseUser
    lateinit var googleSignInOptions: GoogleSignInOptions
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        var passwordVisible = false
        binding.SignInPassword.setOnTouchListener { v, event ->
            val Right = 2
            if (event.getAction() === MotionEvent.ACTION_UP) {
                if (event.getRawX() >= binding.SignInPassword.getRight() - binding.SignInPassword.getCompoundDrawables().get(Right).getBounds().width()) {
                    val selection: Int = binding.SignInPassword.getSelectionEnd()
                    //Handles Multiple option popups
                    if (passwordVisible) {
                        //set drawable image here
                        binding.SignInPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.visibility_off, 0)
                        //for hide password
                        binding.SignInPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                        passwordVisible = false
                    } else {
                        //set drawable image here
                        binding.SignInPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.visibility, 0)
                        //for show password
                        binding.SignInPassword.setTransformationMethod(
                            HideReturnsTransformationMethod.getInstance())
                        passwordVisible = true
                    }
                    binding.SignInPassword.setLongClickable(false) //Handles Multiple option popups
                    binding.SignInPassword.setSelection(selection)
                    return@setOnTouchListener true
                }
            }
            false
        }



        binding.toSignUp.setOnClickListener {
            val intent = Intent(this, signUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.SignInEmail.text.toString()
            val password = binding.SignInPassword.text.toString()
            binding.loginButton.visibility= View.GONE
            binding.progressbar.visibility= View.VISIBLE


            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (password.length > 7) {

                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val u = firebaseAuth.currentUser
                            if (true) {

                                val db = FirebaseDatabase.getInstance().reference
//                                val encryption = Encryption.getDefault("Key", "Salt", ByteArray(16))

                                if (u != null) {
                                    db.child("Users").child(u.uid).addValueEventListener(object:
                                        ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {

//                                            editor.putString("uid", snapshot.child("uid").value.toString().trim())
//                                            editor.putString("name", snapshot.child("name").value.toString().trim())
//                                            editor.putString("age", snapshot.child("age").value.toString().trim())
//                                            editor.putString("email", snapshot.child("email").value.toString().trim())
//                                            editor.putString("phone", snapshot.child("phone").value.toString().trim())
//                                            editor.putString("isDoctor", snapshot.child("doctor").value.toString().trim())
//                                            editor.putString("specialist",snapshot.child("specialist").value.toString().trim())
//                                            editor.putString("stats", snapshot.child("stats").value.toString().trim())
//                                            editor.putString("prescription", snapshot.child("prescription").value.toString().trim())
//                                            editor.putString("upi", snapshot.child(encryption.encrypt("nulla")).value.toString().trim())
//                                            editor.apply()

                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }
                                    })
                                }
                                binding.loginButton.visibility= View.VISIBLE
                                binding.progressbar.visibility= View.GONE
                                startActivity(Intent(this, MainActivity::class.java))

                            } else {
                                if (u != null) {
                                    u.sendEmailVerification()
                                }
                                Toast.makeText(
                                    this,
                                    "Email Verification sent to your mail",
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.loginButton.visibility= View.VISIBLE
                                binding.progressbar.visibility= View.GONE
                            }
                        } else
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        binding.loginButton.visibility= View.VISIBLE
                        binding.progressbar.visibility= View.GONE
                    }
                } else {
                    Toast.makeText(this, "Password length must be greater than 8", Toast.LENGTH_SHORT).show()
                    binding.loginButton.visibility= View.VISIBLE
                    binding.progressbar.visibility= View.GONE
                }

            } else {
                Toast.makeText(this, "Please enter the details!", Toast.LENGTH_SHORT).show()
                binding.loginButton.visibility= View.VISIBLE
                binding.progressbar.visibility= View.GONE
            }
        }


    }
}