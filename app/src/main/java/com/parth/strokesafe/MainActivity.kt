package com.parth.strokesafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.parth.strokesafe.databinding.ActivityMainBinding
import com.parth.strokesafe.ml.LiteModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.FloatBuffer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.predict.setOnClickListener{
            val intent=Intent(this,signUpActivity1::class.java)
            startActivity(intent)
            finish()

//            val inputData = floatArrayOf(1.0f, 2.0f, 3.0f) // replace with your actual input data
//            val byteBuffer = ByteBuffer.allocateDirect(inputData.size * 4)
//            byteBuffer.asFloatBuffer().put(inputData)
//
//            val model = LiteModel.newInstance(this)
//
//            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, inputData.size), DataType.FLOAT32)
//            inputFeature0.loadBuffer(byteBuffer)
//
//            val outputs = model.process(inputFeature0)
//            val outputFeature0 = outputs.outputFeature0AsTensorBuffer
//
//            model.close()
        }
    }
}