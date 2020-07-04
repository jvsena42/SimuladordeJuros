package com.app.simuladordejuros.activity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.app.simuladordejuros.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adView = AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-7567513635988403/6589696253"

        MobileAds.initialize(this)
        mAdView = findViewById(R.id.ad_view)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }

    public fun calc(view: View) {

        val initialValue = id_initial_value.text.toString().toDouble()
        val aplicationsValue = id_aplications_value.text.toString().toDouble()
        val tax = id_tax.text.toString().toDouble() + 1
        val time = id_time.text.toString().toInt()

        var balance: Double

        if (initialValue != null && aplicationsValue != null && tax != null && time != null) {

            balance = initialValue
            var i: Int = 1
            while (i <= time) {
                balance = balance * tax + aplicationsValue
                i++
            }

            id_text_resultado.text = ("Seu saldo será de R$ ${String.format("%.2f",balance)}")

        } else Toast.makeText(this,"Preencha todos os campos",Toast.LENGTH_SHORT).show()
    }
}
