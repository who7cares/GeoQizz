package com.bignerdranch.myapplication

import android.widget.ImageView
import androidx.annotation.StringRes

data class Question(
    @StringRes val textResId: Int,
    val answer: Boolean,
    var buttonState:Boolean = true)
//    var color: ImageView
   {  }
