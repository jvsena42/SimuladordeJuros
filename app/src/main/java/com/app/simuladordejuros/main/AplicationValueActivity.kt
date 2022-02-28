package com.app.simuladordejuros.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.app.simuladordejuros.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_aplication_value.*

class AplicationValueActivity : AppCompatActivity() {

    lateinit var applicationModel: ApplicationModel
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aplication_value)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        applicationModel = intent.extras?.get("initialValue") as ApplicationModel

        //initialize admob
//        MobileAds.initialize(this, "ca-app-pub-7567513635988403~5896288031")

        //load admob
        mAdView = findViewById(R.id.ad_view3)
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

    fun goToTax(view: View){

        val textAplicationValue = id_aplication_value.text.toString()
        if (!textAplicationValue.isNullOrEmpty()){
            val aplicationValue = textAplicationValue.toDouble()
            val i: Intent = Intent(this,TaxActivity::class.java)
            applicationModel.aplicationValue = aplicationValue
            i.putExtra("aplicationValue",applicationModel)
            startActivity(i)
            finish()
        }else{
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }

    }
}
