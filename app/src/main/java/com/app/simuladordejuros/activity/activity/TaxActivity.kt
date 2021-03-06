package com.app.simuladordejuros.activity.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.app.simuladordejuros.R
import com.app.simuladordejuros.activity.model.Aplication
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_tax.*
import java.sql.Time

class TaxActivity : AppCompatActivity() {

    lateinit var aplication: Aplication
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tax)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        aplication = intent.extras?.get("aplicationValue") as Aplication

        //initialize admob
        MobileAds.initialize(this, "ca-app-pub-7567513635988403~5896288031")

        //load admob
        mAdView = findViewById(R.id.ad_view4)
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

    fun goToTime(view: View){

        val textTax = id_tax.text.toString()
        if (!textTax.isNullOrEmpty()){
            val tax = (textTax.toDouble()/100)+1
            val i: Intent = Intent(this,TimeActivity::class.java)
            aplication.tax = tax
            i.putExtra("taxValue",aplication)
            startActivity(i)
            finish()
        }else{
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }

    }
}
