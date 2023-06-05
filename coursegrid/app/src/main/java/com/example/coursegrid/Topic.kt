package com.example.coursegrid

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val nameResID: Int,
    val recommended: Int,
    @DrawableRes val drawableResID: Int,
)
