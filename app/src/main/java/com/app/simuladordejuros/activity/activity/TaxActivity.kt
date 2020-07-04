package com.app.simuladordejuros.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.simuladordejuros.R
import java.sql.Time

class TaxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tax)
    }

    fun goToTime(view: View){
        val i: Intent = Intent(this,TimeActivity::class.java)
        startActivity(i)
    }
}
