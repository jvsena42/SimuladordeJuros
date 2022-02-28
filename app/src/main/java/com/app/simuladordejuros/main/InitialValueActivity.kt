package com.app.simuladordejuros.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.app.simuladordejuros.R
import com.app.simuladordejuros.model.Aplication
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_initial_value.*

class InitialValueActivity : AppCompatActivity() {

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_value)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        //initialize admob
//        MobileAds.initialize(this, "ca-app-pub-7567513635988403~5896288031")

        //load admob
        mAdView = findViewById(R.id.ad_view2)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    override fun onPause() {
        mAdView.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        mAdView.resume()
    }

    override fun onDestroy() {
        mAdView.destroy()
        super.onDestroy()
    }

    fun goToAplicationValue(view: View){

        val textInitialValue = id_initial_value.text.toString()
        if (!textInitialValue.isNullOrBlank()){
            val initialValue = textInitialValue.toDouble()
            val aplication = Aplication( initialValue)
            val i:Intent = Intent(this,AplicationValueActivity::class.java)
            i.putExtra("initialValue",aplication)
            startActivity(i)
            finish()
        }else{
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }

    }
}

