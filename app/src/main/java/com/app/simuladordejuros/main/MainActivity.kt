package com.app.simuladordejuros.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.simuladordejuros.R
import com.app.simuladordejuros.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}