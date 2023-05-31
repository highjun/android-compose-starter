package com.example.tipcalculator

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun calculate_15_percent_tip(){
        composeTestRule.setContent{
            TipCalculatorTheme() {
                TipScreen(name = "Screen!")
            }
        }
        composeTestRule.onNodeWithText("Cost of Service")
            .performTextInput("100")
        val expectedTip = NumberFormat.getCurrencyInstance().format(15)
        composeTestRule.onNodeWithText("Tip amount: $expectedTip").assertExists()
    }
}