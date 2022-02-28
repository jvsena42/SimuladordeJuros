package com.app.simuladordejuros.main.calcScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.simuladordejuros.R
import com.app.simuladordejuros.databinding.FragmentApplicationValueBinding

class ApplicationValueFragment : Fragment() {

    private lateinit var binding: FragmentApplicationValueBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_application_value, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentApplicationValueBinding.bind(view)
    }

}