package com.app.simuladordejuros.main.calcScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.app.simuladordejuros.R
import com.app.simuladordejuros.databinding.FragmentInitialValueBinding
import com.app.simuladordejuros.main.MainViewModel


class InitialValueFragment : Fragment() {

    private lateinit var binding: FragmentInitialValueBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_initial_value, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInitialValueBinding.bind(view)
        onCLick()
    }

    private fun onCLick() = binding.run {
        fabNext.setOnClickListener {
            viewModel.setInitialValue(initialValueETX.text)
        }
    }

}