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
import kotlinx.android.synthetic.main.activity_time.*

class TimeActivity : AppCompatActivity() {

    lateinit var aplication: Aplication
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        aplication = intent.extras?.get("taxValue") as Aplication

        //initialize admob
//        MobileAds.initialize(this, "ca-app-pub-7567513635988403~5896288031")

        //load admob
        mAdView = findViewById(R.id.ad_view5)
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

    fun goToResult(view: View){

        var textTime = id_time.text.toString()
        if (!textTime.isNullOrEmpty()){
            val time = textTime.toDouble()
            aplication.time = time
            val i: Intent = Intent(this,ResultActivity::class.java)
            i.putExtra("time",aplication)
            startActivity(i)
            finish()
        }else{
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }

    }
}
