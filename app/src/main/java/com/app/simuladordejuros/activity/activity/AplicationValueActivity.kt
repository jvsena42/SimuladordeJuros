package com.app.simuladordejuros.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.app.simuladordejuros.R
import com.app.simuladordejuros.activity.model.Aplication
import kotlinx.android.synthetic.main.activity_aplication_value.*

class AplicationValueActivity : AppCompatActivity() {

    lateinit var aplication:Aplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aplication_value)

        aplication = intent.extras.get("initialValue") as Aplication

    }

    fun goToTax(view: View){

        val aplicationValue = id_aplication_value.text.toString().toDouble()
        if (aplicationValue != null){
            val i: Intent = Intent(this,TaxActivity::class.java)
            aplication.aplicationValue = aplicationValue
            i.putExtra("aplicationValue",aplication)
            startActivity(i)
            finish()
        }else{
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }

    }
}
