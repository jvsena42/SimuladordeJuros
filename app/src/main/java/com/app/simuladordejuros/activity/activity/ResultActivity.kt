package com.app.simuladordejuros.activity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.app.simuladordejuros.R
import com.app.simuladordejuros.activity.model.Aplication
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    lateinit var aplication: Aplication
    private var aplications: MutableList<Aplication> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        aplication = intent.extras?.get("time") as Aplication
        calc()
    }

     fun calc() {

        val initialValue = aplication.initialValue
        val aplicationsValue = aplication.aplicationValue
        val tax = aplication.tax
        val time = aplication.time

        var balance = initialValue
        var i: Int = 1
        while (i <= time) {

            aplication.month = i
            aplication.previousBalance = balance
            balance = balance * tax + aplicationsValue
            aplication.balance = balance
            aplications.add(aplication)
            i++
        }

        id_result.text = ("Seu saldo será de R$ ${String.format("%.2f", balance)}")

    }
}
