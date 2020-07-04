package com.app.simuladordejuros.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.simuladordejuros.R

class AplicationValueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aplication_value)
    }

    fun goToTax(view: View){
        val i: Intent = Intent(this,TaxActivity::class.java)
        startActivity(i)
    }
}
