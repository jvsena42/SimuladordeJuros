package com.app.simuladordejuros.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.simuladordejuros.R
import com.app.simuladordejuros.adapter.AdapterAplication
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {

    lateinit var applicationModel: ApplicationModel

    private var applicationModels: MutableList<ApplicationModel> = mutableListOf()
    private lateinit var recyclerResult: RecyclerView
    private lateinit var adapterAplication: AdapterAplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        //initialize components
        recyclerResult = findViewById(R.id.id_recycler_result)

        //configure recyclerview
        adapterAplication = AdapterAplication(applicationModels, this)
        val layoutManager = LinearLayoutManager(this)
        recyclerResult.layoutManager = layoutManager
        recyclerResult.setHasFixedSize(true)
        recyclerResult.adapter = adapterAplication

        applicationModel = intent.extras?.get("time") as ApplicationModel
        calc()

        //initialize graphView
        initializeGraphView()
    }

    fun calc() {
        val initialValue = applicationModel.initialValue
        val aplicationsValue = applicationModel.aplicationValue
        val tax = applicationModel.tax
        val time = applicationModel.time

        var balance = initialValue
        var i: Int = 1
        while (i <= time) {
            applicationModel = ApplicationModel()
            applicationModel.month = i
            applicationModel.tax = tax
            applicationModel.aplicationValue = aplicationsValue
            applicationModel.previousBalance = balance
            balance = balance * tax + aplicationsValue
            applicationModel.balance = balance
            applicationModels.add(applicationModel)
            Log.d("TESTE", applicationModels.toString())
            i++
        }
        adapterAplication.notifyDataSetChanged()

        id_value.text = (String.format("%.2f", balance))

    }

    fun initializeGraphView() {
        val graph = id_graph

        // activate horizontal and vertical zooming and scrolling
        graph.viewport.setScalableY(true)

        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.setMinX(1.0)
        graph.viewport.setMaxX(applicationModel.month.toDouble())

        // set manual Y bounds
        graph.viewport.isYAxisBoundsManual = true
        graph.viewport.setMinY(0.0)
        graph.viewport.setMaxY(applicationModel.balance)

        try {
            var datapoints = mutableListOf<DataPoint>()
            for (app in applicationModels) {
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
        val i: Intent = Intent(this, InitialValueActivity::class.java)
        startActivity(i)
        finish()
    }
}
