package com.app.simuladordejuros.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.simuladordejuros.R
import com.app.simuladordejuros.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observe()
    }

    private fun observe() {
        viewModel.toastMessage.observe(this) { messageRef ->
            Toast.makeText(this, getString(messageRef), Toast.LENGTH_SHORT).show()
        }
    }

}