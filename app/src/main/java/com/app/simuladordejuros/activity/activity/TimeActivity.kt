package com.app.simuladordejuros.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.app.simuladordejuros.R
import com.app.simuladordejuros.activity.model.Aplication
import kotlinx.android.synthetic.main.activity_time.*

class TimeActivity : AppCompatActivity() {

    lateinit var aplication: Aplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        aplication = intent.extras.get("taxValue") as Aplication
    }

    fun goToResult(view: View){

        var time:Double = id_time.text.toString().toDouble()
        if (time!=null){
            val i: Intent = Intent(this,ResultActivity::class.java)
            aplication.time = time
            i.putExtra("time",aplication)
            Log.d("TAX",aplication.toString())
            startActivity(i)
            finish()
        }else{
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }

    }
}
