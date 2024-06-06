package com.example.firebaselogin.parts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hd.charts.PieChartView
import com.hd.charts.common.model.ChartDataSet

@Composable
fun  AddDefaultLineChart() {
    val dataSet = ChartDataSet(
        items = listOf(
            8f, 23f, 54f, 32f, 12f, 37f, 7f, 23f, 43f
        ),
        title = "Line Chart"
    )
    Box(
        modifier = Modifier
            .fillMaxHeight(0.3f)
    ){
        PieChartView(dataSet = dataSet)
    }
}