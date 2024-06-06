package com.example.firebaselogin.parts

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable

@Composable
fun mapAmountToColor(category: String): Color {

    return when (category){
        "Income" -> Color(0xFF41C7AF)
        //"Expense" -> Color.Red
        else -> Color.Red
    }
}