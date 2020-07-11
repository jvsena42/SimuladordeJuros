package com.app.simuladordejuros.activity.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.simuladordejuros.R
import com.app.simuladordejuros.activity.adapter.AdapterAplication
import com.app.simuladordejuros.activity.model.Aplication
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {

    lateinit var aplication: Aplication

    private var aplications: MutableList<Aplication> = mutableListOf()
    private lateinit var recyclerResult: RecyclerView
    private lateinit var adapterAplication: AdapterAplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //initialize components
        recyclerResult = findViewById(R.id.id_recycler_result)

        //configure recyclerview
        adapterAplication = AdapterAplication(aplications, this)
        val layoutManager = LinearLayoutManager(this)
        recyclerResult.layoutManager = layoutManager
        recyclerResult.setHasFixedSize(true)
        recyclerResult.adapter = adapterAplication

        aplication = intent.extras?.get("time") as Aplication
        calc()

        //initialize graphView
        initializeGraphView()
    }

    fun calc() {
        val initialValue = aplication.initialValue
        val aplicationsValue = aplication.aplicationValue
        val tax = aplication.tax
        val time = aplication.time

        var balance = initialValue
        var i: Int = 1
        while (i <= time) {
            aplication = Aplication()
            aplication.month = i
            aplication.tax = tax
            aplication.aplicationValue = aplicationsValue
            aplication.previousBalance = balance
            balance = balance * tax + aplicationsValue
            aplication.balance = balance
            aplications.add(aplication)
            Log.d("TESTE", aplications.toString())
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
        graph.viewport.setMaxX(aplication.month.toDouble())

        // set manual Y bounds
        graph.viewport.isYAxisBoundsManual = true
        graph.viewport.setMinY(0.0)
        graph.viewport.setMaxY(aplication.balance)

        try {
            var datapoints = mutableListOf<DataPoint>()
            for (app in aplications) {
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
