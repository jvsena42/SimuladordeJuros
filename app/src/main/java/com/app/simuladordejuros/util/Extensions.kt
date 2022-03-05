package com.app.simuladordejuros.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Double.twoDecimal() = String.format("%.2f", this)
fun Double.threeDecimal() = String.format("%.3f", this)

fun View.showSnackbar(message : String) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    snackBar.show()
}