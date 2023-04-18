package com.parth.strokesafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.parth.strokesafe.databinding.ActivitySignUp2Binding

private lateinit var binding: ActivitySignUp2Binding
class signUpActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val items= listOf("Yes","No")
        val adapter= ArrayAdapter(this,R.layout.list_items,items)
        val gluc=intent.extras!!.getString("gluc").toString()
        val cholestrol=intent.extras!!.getString("cholestrol").toString()
        val height=intent.extras!!.getString("height").toString()
        val weight=intent.extras!!.getString("weight").toString()
        binding.alcohol1.setAdapter(adapter)
        binding.cardio1.setAdapter(adapter)
        binding.smoke1.setAdapter(adapter)
        binding.active1.setAdapter(adapter)
        binding.createAccount.setOnClickListener{

            if(binding.smoke1.text.isNotEmpty()&& binding.alcohol1.text.isNotEmpty()&& binding.cardio1.text.isNotEmpty()){
                val intent=Intent(this,ResultActivity::class.java)
                intent.putExtra("gluc",gluc)
                intent.putExtra("cholestrol",cholestrol )
                intent.putExtra("height", height)
                intent.putExtra("weight", weight)
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