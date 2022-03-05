package com.app.simuladordejuros.main.calcScreens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.simuladordejuros.R
import com.app.simuladordejuros.adapter.AdapterResult
import com.app.simuladordejuros.databinding.FragmentResultBinding
import com.app.simuladordejuros.main.CapitalInvestment
import com.app.simuladordejuros.main.MainActivity
import com.app.simuladordejuros.main.MainViewModel
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var adapterResult: AdapterResult

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        onCLick()
        initRecyclerView()
        observe()
    }

    private fun initRecyclerView() = binding.resultVR.run {
        adapterResult = AdapterResult()
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
        adapter = adapterResult
    }

    private fun observe() {
        viewModel.resultList.observe(viewLifecycleOwner) { list ->
            initializeGraphView(list)
        }
        viewModel.amountText.observe(viewLifecycleOwner) { totalValue ->
            binding.valueTX.text = totalValue
        }
    }

    private fun onCLick() = binding.run {
        calcAgainBTN.setOnClickListener {
            startActivity(Intent(requireActivity(),MainActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun initializeGraphView(investmentList: List<CapitalInvestment>) = binding.graph.run {

        viewport.apply {
            // activate horizontal and vertical zooming and scrolling
            setScalableY(true)

            isXAxisBoundsManual = true
            setMinX(1.0)
            setMaxX(investmentList.last().month.toDouble())

            // set manual Y bounds
            isYAxisBoundsManual = true
            setMinY(0.0)
            setMaxY(investmentList.last().balance)
        }

        try {
            val dataPoints = mutableListOf<DataPoint>()
            investmentList.forEach { application ->
                val dataPoint = DataPoint(application.month.toDouble(), application.balance)
                dataPoints.add(dataPoint)
            }

            val series: LineGraphSeries<DataPoint> = LineGraphSeries<DataPoint>(
                dataPoints.toTypedArray()
            )
            addSeries(series)
        } catch (e: IllegalArgumentException) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
        }
    }

}