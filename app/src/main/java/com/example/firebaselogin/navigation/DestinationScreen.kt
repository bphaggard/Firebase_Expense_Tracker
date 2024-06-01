package com.example.firebaselogin.navigation

import com.example.firebaselogin.R

sealed class DestinationScreen(val route: String, val icon: Int, val label: String) {
    data object Main: DestinationScreen("main", 0, "Main")
    data object SignUp: DestinationScreen("signup", 0, "SignUp")
    data object Login: DestinationScreen("login", 0, "Login")
    data object Home: DestinationScreen("home", R.drawable.rounded_home, "Home")
    data object Add: DestinationScreen("add", R.drawable.round_add_circle, "Add")
    data object Transactions: DestinationScreen("transactions", R.drawable.rounded_compare_arrows, "Transactions")
}