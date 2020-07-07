package com.app.simuladordejuros.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.app.simuladordejuros.R
import com.app.simuladordejuros.activity.model.Aplication
import kotlinx.android.synthetic.main.activity_initial_value.*

class InitialValueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_value)
    }

    fun goToAplicationValue(view: View){

        val initialValue = id_initial_value.text.toString().toDouble()
        if (initialValue != null){
            val aplication = Aplication( initialValue)
            val i:Intent = Intent(this,AplicationValueActivity::class.java)
            i.putExtra("initialValue",aplication)
            startActivity(i)
        }else{
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }

    }
}

