package com.app.simuladordejuros.main.calcScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.simuladordejuros.R
import com.app.simuladordejuros.databinding.FragmentTaxBinding
import com.app.simuladordejuros.main.MainViewModel


class TaxFragment : Fragment() {

    private lateinit var binding: FragmentTaxBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tax, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTaxBinding.bind(view)
        onCLick()
    }

    private fun onCLick() = binding.run {
        fabNext.setOnClickListener {
            viewModel.setTaxValue(taxETX.text)
        }
    }
}