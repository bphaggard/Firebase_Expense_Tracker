package com.example.firebaselogin.navigation

import com.example.firebaselogin.R

sealed class BottomBarScreen(val route: String, val icon: Int, val label: String) {
    object Home : BottomBarScreen(
        "home", R.drawable.rounded_home, "Home"
    )
    object Add : BottomBarScreen(
        "add", R.drawable.round_add_circle, "Add"
    )
    object Transactions : BottomBarScreen(
        "transactions", R.drawable.rounded_compare_arrows, "Transactions"
    )
}