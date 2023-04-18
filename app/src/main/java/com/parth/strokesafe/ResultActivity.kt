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
        var number = random.nextInt(60) + 5
        val gluc=intent.extras!!.getString("gluc").toString()
        val cholestrol=intent.extras!!.getString("cholestrol").toString()
        val height=intent.extras!!.getString("height").toString()
        val weight=intent.extras!!.getString("weight").toString()
        val smoke=intent.extras!!.getString("smoke").toString()
        val alcohol=intent.extras!!.getString("alcohol").toString()
        val cardio=intent.extras!!.getString("cardio").toString()

        if(cholestrol=="well above normal"||gluc=="well above normal"||smoke=="Yes"||alcohol=="Yes"||cardio=="Yes")
            number = random.nextInt(25) + 70

        binding.result.text="You have a supposed chance of "+number.toString()+"% to have a stroke!!"
    }
}












