package com.parth.strokesafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.parth.strokesafe.databinding.ActivitySignUp2Binding

private lateinit var binding: ActivitySignUp2Binding
class signUpActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createAccount.setOnClickListener{

            if(binding.smoke1.text.isNotEmpty()&& binding.alcohol1.text.isNotEmpty()&& binding.cardio1.text.isNotEmpty()){
                val intent=Intent(this,MainActivity::class.java)
                intent.putExtra("age",intent.extras!!.getString("age").toString())
                intent.putExtra("gender", intent.extras!!.getString("gender").toString())
                intent.putExtra("height", intent.extras!!.getString("height").toString())
                intent.putExtra("weight", intent.extras!!.getString("weight").toString())
                intent.putExtra("smoke", binding.smoke1.text.toString().trim())
                intent.putExtra("alcohol", binding.alcohol1.text.toString().trim())
                intent.putExtra("cardio", binding.cardio1.text.toString().trim())
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Please enter all the details", Toast.LENGTH_LONG).show()
            }
        }
    }
}