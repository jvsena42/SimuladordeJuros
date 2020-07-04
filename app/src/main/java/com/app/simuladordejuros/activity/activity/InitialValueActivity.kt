package com.app.simuladordejuros.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.simuladordejuros.R

class InitialValueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_value)
    }

    fun goToAplicationValue(view: View){
        val i:Intent = Intent(this,AplicationValueActivity::class.java)
        startActivity(i)
    }
}
