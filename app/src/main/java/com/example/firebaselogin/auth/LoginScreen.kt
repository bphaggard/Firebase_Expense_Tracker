package com.example.firebaselogin.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaselogin.FbViewModel
import com.example.firebaselogin.R
import com.example.firebaselogin.navigation.DestinationScreen
import com.example.firebaselogin.ui.theme.leagueFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController : NavController , viewModel : FbViewModel) {
    val fieldColor = MaterialTheme.colorScheme.onBackground
    var isFocused by remember { mutableStateOf(false) }
    val empty by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var errorE by remember { mutableStateOf(false) }
    var errorP by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)){
        Image(
            painter = painterResource(
                id = if (!isSystemInDarkTheme()) R.drawable.login_back
                else R.drawable.login_back_light) ,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()
                .blur(if (isFocused) 10.dp else 0.dp)
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (viewModel.inProgress.value) {
            CircularProgressIndicator()
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(top = 50.dp)
    ) {
        Text(
            text = "Welcome\n\nBack!",
            fontFamily = leagueFamily ,
            color = fieldColor,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            modifier = Modifier.width(300.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        if (errorE) {
            Text(
                text = "Enter email",
                color = Color.Red,
                modifier = Modifier.padding(end = 100.dp)
            )
        }
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.person) ,
                    contentDescription = null)
            },
            trailingIcon = {
                if (email.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.close) ,
                        contentDescription = null,
                        modifier = Modifier.clickable { email = empty }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ) ,
            singleLine = true,
            textStyle = TextStyle(
                color = fieldColor,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ) ,
            shape = RoundedCornerShape(20.dp) ,
            modifier = Modifier
                .width(300.dp)
                .height(60.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = fieldColor,
                focusedIndicatorColor = fieldColor,
                cursorColor = fieldColor,
                containerColor = MaterialTheme.colorScheme.background,
                focusedLeadingIconColor = fieldColor,
                unfocusedLeadingIconColor = fieldColor,
                focusedLabelColor = fieldColor,
                unfocusedLabelColor = fieldColor,
                focusedTrailingIconColor = fieldColor,
                unfocusedTrailingIconColor = fieldColor,
            )
        )
        Spacer(modifier = Modifier.height(30.dp))
        if (errorP) {
            Text(
                text = "Enter Password",
                color = Color.Red,
                modifier = Modifier.padding(end = 100.dp)
            )
        }
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.lock) ,
                    contentDescription = null)
            },
            trailingIcon = {
                if (password.isNotEmpty()) {
                    val visibilityIcon = if (passwordVisibility) {
                        painterResource(id = R.drawable.visibility)
                    } else {
                        painterResource(id = R.drawable.visibility_off)
                    }
                    Icon(
                        painter = visibilityIcon,
                        contentDescription = if (passwordVisibility) "Hide Password" else "Show Password",
                        modifier = Modifier.clickable {
                            passwordVisibility = !passwordVisibility
                        }
                    )
                }
            },
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ) ,
            singleLine = true,
            textStyle = TextStyle(
                color = fieldColor,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ) ,
            shape = RoundedCornerShape(20.dp) ,
            modifier = Modifier
                .width(300.dp)
                .height(60.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = fieldColor,
                focusedIndicatorColor = fieldColor,
                cursorColor = fieldColor,
                containerColor = MaterialTheme.colorScheme.background,
                focusedLeadingIconColor = fieldColor,
                unfocusedLeadingIconColor = fieldColor,
                focusedLabelColor = fieldColor,
                unfocusedLabelColor = fieldColor,
                focusedTrailingIconColor = fieldColor,
                unfocusedTrailingIconColor = fieldColor
            )
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {
            if (email.isNotEmpty()) {
                errorE = false
                if (password.isNotEmpty()) {
                    errorP = false
                    viewModel.login(email, password)
                }
                else {
                    errorP = true
                }
            }
            else {
                errorE = true
            }
        },
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier
                .width(200.dp)
                .height(60.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp)
        ) {
            Text(
                text = "Log In",
                fontSize = 22.sp,
                fontFamily = leagueFamily ,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don´t have an account?",
                fontFamily = leagueFamily,
                fontSize = 16.sp,
                color = fieldColor
            )
            TextButton(
                onClick = { navController.navigate(DestinationScreen.SignUp.route) }
            ) {
                Text(
                    text = "Create one",
                    fontFamily = leagueFamily,
                    fontSize = 16.sp
                )
            }
        }
        if (viewModel.signedIn.value) {
            navController.navigate(DestinationScreen.Home.route) {
                popUpTo(navController.graph.id) { inclusive = true }
            }
        }
        viewModel.signedIn.value = false
    }
}