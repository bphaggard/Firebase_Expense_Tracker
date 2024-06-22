package com.example.firebaselogin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.example.firebaselogin.elements.ConnectivityObserver
import com.example.firebaselogin.elements.NetworkConnectivityObserver
import com.example.firebaselogin.navigation.AppScaffold
import com.example.firebaselogin.ui.theme.FirebaseLoginTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val transactionViewModel: TransactionViewModel by viewModels()

    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        WindowCompat.setDecorFitsSystemWindows(this.window, false)
        setContent {
            window.statusBarColor = getColor(R.color.black)
            window.navigationBarColor = getColor(R.color.black)
            FirebaseLoginTheme {
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )
                Toast.makeText(LocalContext.current, "Internet connection: $status", Toast.LENGTH_SHORT).show()
                AppScaffold(transactionViewModel)
            }
        }
    }
}