package com.app.simuladordejuros.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.app.simuladordejuros.R
import com.app.simuladordejuros.activity.model.Aplication
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_aplication_value.*

class AplicationValueActivity : AppCompatActivity() {

    lateinit var aplication:Aplication
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aplication_value)

        aplication = intent.extras.get("initialValue") as Aplication

        //initialize admob
        MobileAds.initialize(this, "ca-app-pub-7567513635988403~5896288031")

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
