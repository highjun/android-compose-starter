package com.example.artspace

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.artspace.ui.theme.ArtspaceTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    val image_list = listOf<ImageData>(
        ImageData(R.drawable.cat, "Cat", "Google Images", 2022),
        ImageData(R.drawable.sky, "Sky", "Google Images", 2019),
        ImageData(R.drawable.tiger1, "Tiger", "Google Images", 2014),
        ImageData(R.drawable.mountain2, "Mountain", "Google Images", 2021)
    )

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun clickNext() {
        composeTestRule.setContent {
            ArtspaceTheme() {
                ArtSpaceScreen(image_list = image_list)
            }
        }
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Sky").assertExists()

    }
    @Test
    fun clickNextWhenLast() {
        composeTestRule.setContent {
            ArtspaceTheme() {
                ArtSpaceScreen(image_list = image_list)
            }
        }

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Cat").assertExists()

    }
    @Test
    fun clickPrevious() {
        composeTestRule.setContent {
            ArtspaceTheme() {
                    ArtSpaceScreen(image_list = image_list)
            }
        }

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Previous").performClick()
        composeTestRule.onNodeWithText("Cat").assertExists()
    }

    @Test
    fun clickPreviousWhenFirst() {
        composeTestRule.setContent {
            ArtspaceTheme() {
                ArtSpaceScreen(image_list = image_list)
            }
        }
        composeTestRule.onNodeWithText("Previous").performClick()
        composeTestRule.onNodeWithText("Mountain").assertExists()
    }

}