package com.parth.strokesafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.parth.strokesafe.databinding.ActivitySignUp1Binding

private lateinit var binding: ActivitySignUp1Binding

class signUpActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUp1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val items= listOf("Male","Female","Prefer not to say")
        val adapter=ArrayAdapter(this,R.layout.list_items,items)
        binding.gender1.setAdapter(adapter)

        binding.createAccount.setOnClickListener{
            if(binding.age.text.isNotEmpty()&& binding.gender1.text.isNotEmpty()&& binding.height.text.isNotEmpty()&& binding.weight.text.isNotEmpty()){
                val intent=Intent(this,signUpActivity2::class.java)
                intent.putExtra("age", binding.age.text.toString().trim())
                intent.putExtra("gender", binding.gender1.text.toString().trim())
                intent.putExtra("height", binding.height.text.toString().trim())
                intent.putExtra("weight", binding.weight.text.toString().trim())
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Please enter all the details",Toast.LENGTH_LONG).show()
            }
        }
    }
}