package com.app.simuladordejuros.main.calcScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.app.simuladordejuros.R
import com.app.simuladordejuros.databinding.FragmentTimeBinding
import com.app.simuladordejuros.main.MainViewModel

class TimeFragment : Fragment() {

    private lateinit var binding: FragmentTimeBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTimeBinding.bind(view)
        onCLick()
    }

    private fun onCLick() = binding.run {
        fabNext.setOnClickListener {
            viewModel.setTimeValue(timeETX.text)
        }
    }
}