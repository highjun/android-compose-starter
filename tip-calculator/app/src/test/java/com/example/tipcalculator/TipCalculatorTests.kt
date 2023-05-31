package com.example.tipcalculator

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculate_20_percent_tip() {
        val amount = 100.00
        val tipPercent = 20.00
        assertEquals(calcTip(amount, tipPercent), NumberFormat.getCurrencyInstance().format(20))
    }
}