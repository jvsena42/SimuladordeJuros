package com.app.simuladordejuros.model

import java.io.Serializable

data class Aplication  (
    var initialValue: Double =0.0,
    var aplicationValue: Double = 0.0,
    var tax: Double = 0.0,
    var time: Double = 0.0,
    var month: Int =1,
    var previousBalance: Double = 0.0,
    var balance: Double = 0.0
): Serializable {
}