package com.parth.strokesafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.parth.strokesafe.databinding.ActivityResultBinding
import java.util.*

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val random = Random()
        val number = random.nextInt(90) + 5
        binding.result.text="You have a supposed chance of "+number.toString()+"% to have a stroke!!"
    }
}












