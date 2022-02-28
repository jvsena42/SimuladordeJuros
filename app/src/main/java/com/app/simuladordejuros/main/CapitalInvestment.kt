package com.app.simuladordejuros.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CapitalInvestment  (
    var initialValue: Double = 0.0,
    var applicationValue: Double = 0.0,
    var tax: Double = 0.0,
    var time: Double = 0.0,
    var month: Int = 1,
    var previousBalance: Double = 0.0,
    var balance: Double = 0.0
): Parcelable