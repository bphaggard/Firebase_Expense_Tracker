package com.example.firebaselogin.parts

import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun isSmallScreenHeight(): Boolean {
    val conf = LocalConfiguration.current
    return conf.screenHeightDp <= 786
} // function to check screen size to update UI for the best result

@Composable
fun rememberImeState(): State<Boolean> {
    val imeState = remember {
        mutableStateOf(false)
    }
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
            imeState.value = isKeyboardOpen
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
    return imeState
} // function to disable content keyboard overlay

fun getFormattedDate(transactionDate: String): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Adjust format as per your date string format
    val date = sdf.parse(transactionDate)
    val calendar = Calendar.getInstance()
    val today = Calendar.getInstance()
    val yesterday = Calendar.getInstance().apply { add(Calendar.DATE, -1) }

    return when {
        sdf.format(calendar.time) == date?.let { sdf.format(it) } -> "Today"
        sdf.format(yesterday.time) == date?.let { sdf.format(it) } -> "Yesterday"
        else -> transactionDate
    }
}