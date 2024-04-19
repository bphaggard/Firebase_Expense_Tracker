package com.example.firebaselogin.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.firebaselogin.R

// Set of Material typography styles to start with
val bebasNeueFamily = FontFamily(
    Font(R.font.bebasneue, FontWeight.Normal)
)

val djbCoffeeFamily = FontFamily(
    Font(R.font.djbcoffee, FontWeight.Normal)
)
val madimiFamily = FontFamily(
    Font(R.font.madimi_regular, FontWeight.Normal)
)
val leagueFamily = FontFamily(
    Font(R.font.leaguespartan_regular, FontWeight.Normal)
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default ,
        fontWeight = FontWeight.Normal ,
        fontSize = 16.sp ,
        lineHeight = 24.sp ,
        letterSpacing = 0.5.sp
    )
)