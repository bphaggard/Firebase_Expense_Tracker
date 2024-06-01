package com.example.firebaselogin.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaselogin.TransactionViewModel
import com.example.firebaselogin.models.Transactions
import com.example.firebaselogin.navigation.BottomNavigationBar
import com.example.firebaselogin.ui.theme.leagueFamily
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddScreen(
    navController: NavController,
    viewModel: TransactionViewModel
){

    var amount by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    val category by viewModel.selectedCategory.collectAsState()
    val categories = listOf("Food", "Entertainment", "Shopping", "Travel", "Other", "Income", "Expense","Groceries")
    var isExpanded by remember { mutableStateOf(false) }

    Scaffold(
        content = { innerPadding ->
                  Column(
                      modifier = Modifier
                          .fillMaxSize()
                          .padding(innerPadding),
                      verticalArrangement = Arrangement.Center,
                      horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                      Text(
                          text = "Add Transaction",
                          fontSize = 22.sp,
                          fontFamily = leagueFamily,
                          fontWeight = FontWeight.Bold
                      )
                      Spacer(modifier = Modifier.height(20.dp))
                      OutlinedTextField(
                          value = amount,
                          onValueChange = { amount = it },
                          label = { Text(text = "Amount")},
                          placeholder = {Text("Enter Amount")},
                          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                      )
                      Spacer(modifier = Modifier.height(20.dp))
                      OutlinedTextField(
                          value = title,
                          onValueChange = { title = it },
                          label = { Text(text = "Title")},
                          placeholder = {Text("Enter Title")}
                      )
                      Spacer(modifier = Modifier.height(20.dp))
                      ExposedDropdownMenuBox(
                          expanded = isExpanded,
                          onExpandedChange = {isExpanded = !isExpanded}
                      ) {
                          OutlinedTextField(
                              modifier = Modifier.menuAnchor(),
                              value = category,
                              onValueChange = {},
                              readOnly = true,
                              label = { Text(text = "Category")},
                              trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)},
                              colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                          )
                          ExposedDropdownMenu(
                              expanded = isExpanded,
                              onDismissRequest = { isExpanded = false }
                          ) {
                              categories.forEach{ category ->
                                  DropdownMenuItem(
                                      text = { Text(text = category) },
                                      onClick = {
                                          viewModel.setSelectedCategory(category)
                                          isExpanded = false
                                      },
                                      contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                  )
                              }
                          }
                      }
                      Spacer(modifier = Modifier.height(50.dp))
                      Button(
                          onClick = {
                                    val transaction = Transactions(
                                        title = title,
                                        category = category,
                                        amount = amount,
                                        date = LocalDate.now().toString()
                                    )
                              viewModel.insertTransaction(transaction)
                              title = ""
                              amount = ""
                              viewModel.setSelectedCategory("")
                          },
                          colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                          modifier = Modifier.size(150.dp,50.dp),
                          shape = RoundedCornerShape(20.dp),
                          elevation = ButtonDefaults.buttonElevation(
                              defaultElevation = 6.dp,
                              pressedElevation = 8.dp,
                              disabledElevation = 0.dp)
                      ) {
                          Text(
                              text = "SAVE",
                              fontFamily = leagueFamily,
                              fontSize = 18.sp,
                              fontWeight = FontWeight.Bold)
                      }
                  }
        }
    )
}