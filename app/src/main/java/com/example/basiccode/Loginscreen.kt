package com.example.basiccode

import android.R.attr.text
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private const val CORRECT_USERNAME = "user"
private const val CORRECT_PASSWORD = "password123"

@Composable
fun StudentAppNavigation(){
    val navController = rememberNavController()
        val studentList = remember { mutableStateListOf<Student>() }
        NavHost(navController = navController, startDestination = "LoginScreen"){
            composable("DashboardScreen"){
                DashboardScreen(
                    students = studentList,
                    onBackToLogin = { navController.navigate("LoginScreen") }
                )
            }
            composable("AddsStudentScreen"){
                AddStudentScreen(
                    students = studentList,
                    onBackToDashboard = { navController.navigate("DashboardScreen") }
                )
            }
        }
    }

@Composable
fun Loginscreen(onNavigateToDashboard: () -> Unit){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var verificationMessage by remember { mutableStateOf("Enter credentials and click Login") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        Row {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
        }
        Row {
            Button(
                onClick = {
                    if (username == CORRECT_USERNAME && password == CORRECT_PASSWORD) {
                        verificationMessage = "Login Successful! 🎉"
                    } else {
                        verificationMessage = "Login Failed. Invalid username or password."
                    }
                },
                enabled = username.isNotEmpty() && password.isNotEmpty(),
                modifier = Modifier.padding(3.dp)
                    .size(width = 200.dp, height = 40.dp),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Login")
            }
        }
        Row {
            Text(
                text = verificationMessage,
                style = MaterialTheme.typography.bodyLarge,
                color = when {
                    verificationMessage.contains("Successful") -> MaterialTheme.colorScheme.primary
                    verificationMessage.contains("Failed") -> MaterialTheme.colorScheme.error
                    else -> MaterialTheme.colorScheme.onSurface
                }
            )
        }
    }
}

//@Composable
//fun LoginButton(
//    text: String,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    backgroundColor: Color = Color(0xFF333333),
//    textColor: Color = Color.White,
//    height: Dp = 40.dp,
//    width: Dp = 200.dp
//) {
//    Button(
//        onClick = onClick,
//        modifier = modifier
//            .padding(3.dp)
//            .size(width = width, height = height),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = backgroundColor
//        ),
//        shape = RoundedCornerShape(12.dp),
//        contentPadding = PaddingValues(0.dp)
//    ) {
//        Text(
//            text = text,
//            color = textColor,
//            fontSize = 16.sp
//        )
//    }
//}