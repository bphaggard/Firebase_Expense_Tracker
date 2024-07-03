package com.example.firebaselogin.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.firebaselogin.ui.theme.leagueFamily

@Composable
fun TransactionCard(
    image: Int,
    title: String,
    amount: String,
    date: String,
    color: Color,
    onDismiss: () -> Unit
){
    val isExpanded = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(
                text = "Remove this transaction?",
                fontFamily = leagueFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
            },
            confirmButton = {
                Button(onClick = {
                    showDialog.value = false }) {
                    Text(text = "Close")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog.value = false
                    onDismiss()
                }) {
                    Text(text = "Delete")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight(Alignment.CenterVertically)
            .clickable { showDialog.value = true },
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.width(14.dp))
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                modifier = Modifier
                    .padding(bottom = 47.dp)
                    .fillMaxWidth(0.5f)
                    .clickable { isExpanded.value = !isExpanded.value },
                text = title,
                fontSize = 22.sp,
                fontFamily = leagueFamily,
                maxLines = if (isExpanded.value) Int.MAX_VALUE else 1,
                overflow = if (isExpanded.value) TextOverflow.Visible else TextOverflow.Ellipsis
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$amount Kč",
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = date,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}