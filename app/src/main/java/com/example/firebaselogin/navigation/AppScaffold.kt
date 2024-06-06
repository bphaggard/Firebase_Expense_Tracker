package com.example.firebaselogin.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.firebaselogin.FbViewModel
import com.example.firebaselogin.TransactionViewModel
import com.example.firebaselogin.auth.HomeScreen
import com.example.firebaselogin.auth.LoginScreen
import com.example.firebaselogin.auth.MainScreen
import com.example.firebaselogin.auth.SignUpScreen
import com.example.firebaselogin.screens.AddScreen
import com.example.firebaselogin.screens.TransactionScreen

@Composable
fun AppScaffold(viewModel: TransactionViewModel) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val showBars = currentRoute in listOf(
        DestinationScreen.Home.route,
        DestinationScreen.Add.route,
        DestinationScreen.Transactions.route
    )

    Scaffold(
        bottomBar = {
            if (showBars) {
                BottomNavigationBar(navController)
            }
        }
    ) {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            viewModel
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: TransactionViewModel,
) {
    val fbViewModel = hiltViewModel<FbViewModel>()

    NavHost(
        navController = navController,
        startDestination = DestinationScreen.Main.route,
        modifier = modifier
    ) {
        composable(DestinationScreen.Main.route) {
            MainScreen(navController)
        }
        composable(DestinationScreen.SignUp.route) {
            SignUpScreen(navController, fbViewModel)
        }
        composable(DestinationScreen.Login.route) {
            LoginScreen(navController, fbViewModel)
        }
        composable(DestinationScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(DestinationScreen.Add.route) {
            AddScreen(viewModel)
        }
        composable(DestinationScreen.Transactions.route) {
            TransactionScreen(viewModel)
        }
    }
}
