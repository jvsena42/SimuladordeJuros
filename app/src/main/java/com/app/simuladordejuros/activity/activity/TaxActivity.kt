package com.app.simuladordejuros.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.app.simuladordejuros.R
import com.app.simuladordejuros.activity.model.Aplication
import kotlinx.android.synthetic.main.activity_tax.*
import java.sql.Time

class TaxActivity : AppCompatActivity() {

    lateinit var aplication: Aplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tax)

        aplication = intent.extras.get("aplicationValue") as Aplication

    }

    fun goToTime(view: View){

        val tax:Double = id_tax.text.toString().toDouble()
        if (tax!= null){
            val i: Intent = Intent(this,TimeActivity::class.java)
            aplication.tax = tax
            i.putExtra("taxValue",aplication)
            startActivity(i)
        }else{
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }

    }
}
