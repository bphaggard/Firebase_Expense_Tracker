package com.example.firebaselogin.main

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.firebaselogin.FbViewModel

@Composable
fun NotificationMessage(viewModel : FbViewModel) {
    val notifState = viewModel.popUpNotification.value
    val notifMessage = notifState?.getContentOrNull()
    if (notifMessage != null) {
        Toast.makeText(LocalContext.current, notifMessage, Toast.LENGTH_SHORT).show()
    }
}