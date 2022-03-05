package com.app.simuladordejuros.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import com.app.simuladordejuros.R
import com.app.simuladordejuros.databinding.MainActivityBinding
import com.app.simuladordejuros.util.showSnackbarRed

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
            binding.root.showSnackbarRed(getString(messageRef))
        }
        viewModel.nextScreen.observe(this) {
            findNavController(this, R.id.navHost).navigate(it)
        }
    }
}