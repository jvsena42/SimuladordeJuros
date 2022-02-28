package com.app.simuladordejuros.main.calcScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.app.simuladordejuros.R
import com.app.simuladordejuros.adapter.AdapterApplication
import com.app.simuladordejuros.databinding.FragmentResultBinding
import com.app.simuladordejuros.main.CapitalInvestment
import com.app.simuladordejuros.main.MainViewModel
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_result.*

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var adapterApplication: AdapterApplication

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
        observe()
    }

    private fun observe() {
        viewModel.resultList.observe(viewLifecycleOwner) { list ->
            initializeGraphView(list)
        }

    }

    private fun onCLick() = binding.run {
        calcAgainBTN.setOnClickListener {

        }
    }

    fun initializeGraphView(investmentList: List<CapitalInvestment>) = binding.graph.run {

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