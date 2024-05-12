package com.example.firebaselogin.navigation

import com.example.firebaselogin.R

data class BottomNavigationItem(
    val title: String,
    val icon: Int,
    val route: String
)

val items = listOf(
    BottomNavigationItem(
        title = "Home",
        icon = R.drawable.rounded_home,
        route = "home"
    ),
    BottomNavigationItem(
        title = "Add",
        icon = R.drawable.round_add_circle,
        route = "add"
    ),
    BottomNavigationItem(
        title = "Transactions",
        icon = R.drawable.rounded_compare_arrows,
        route = "transactions"
    )
)