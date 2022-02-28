package com.app.simuladordejuros.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.simuladordejuros.R
import com.app.simuladordejuros.adapter.AdapterApplication
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {

    lateinit var capitalInvestment: CapitalInvestment

    private var capitalInvestments: MutableList<CapitalInvestment> = mutableListOf()
    private lateinit var recyclerResult: RecyclerView
    private lateinit var adapterApplication: AdapterApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        //initialize components
        recyclerResult = findViewById(R.id.id_recycler_result)

        //configure recyclerview
        adapterApplication = AdapterApplication(capitalInvestments, this)
        val layoutManager = LinearLayoutManager(this)
        recyclerResult.layoutManager = layoutManager
        recyclerResult.setHasFixedSize(true)
        recyclerResult.adapter = adapterApplication

        capitalInvestment = intent.extras?.get("time") as CapitalInvestment
        calc()

        //initialize graphView
        initializeGraphView()
    }

    fun calc() {
        val initialValue = capitalInvestment.initialValue
        val aplicationsValue = capitalInvestment.applicationValue
        val tax = capitalInvestment.tax
        val time = capitalInvestment.time

        var balance = initialValue
        var i: Int = 1
        while (i <= time) {
            capitalInvestment = CapitalInvestment()
            capitalInvestment.month = i
            capitalInvestment.tax = tax
            capitalInvestment.applicationValue = aplicationsValue
            capitalInvestment.previousBalance = balance
            balance = balance * tax + aplicationsValue
            capitalInvestment.balance = balance
            capitalInvestments.add(capitalInvestment)
            Log.d("TESTE", capitalInvestments.toString())
            i++
        }
        adapterApplication.notifyDataSetChanged()

        id_value.text = (String.format("%.2f", balance))

    }

    fun initializeGraphView() {
        val graph = id_graph

        // activate horizontal and vertical zooming and scrolling
        graph.viewport.setScalableY(true)

        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.setMinX(1.0)
        graph.viewport.setMaxX(capitalInvestment.month.toDouble())

        // set manual Y bounds
        graph.viewport.isYAxisBoundsManual = true
        graph.viewport.setMinY(0.0)
        graph.viewport.setMaxY(capitalInvestment.balance)

        try {
            var datapoints = mutableListOf<DataPoint>()
            for (app in capitalInvestments) {
                var dataPoint: DataPoint = DataPoint(app.month.toDouble(), app.balance)
                datapoints.add(dataPoint)
            }

            val series: LineGraphSeries<DataPoint> = LineGraphSeries<DataPoint>(
                datapoints.toTypedArray()
            )
            graph.addSeries(series)
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun backToStart(view: View) {
        /*val i: Intent = Intent(this, InitialValueActivity::class.java)
        startActivity(i)
        finish()*/
    }
}
