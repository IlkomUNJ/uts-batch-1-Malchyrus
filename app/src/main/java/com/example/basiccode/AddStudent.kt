package com.example.basiccode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment

@Composable
fun AddStudentScreen(
    students: MutableList<Student>,
    onBackToDashboard: () -> Unit
){
    var ID by remember { mutableStateOf("") }
    var Name by remember { mutableStateOf("") }
    var Phone by remember { mutableStateOf("") }
    var Address by remember { mutableStateOf("") }
    val isFormValid = ID.isNotBlank() && Name.isNotBlank() &&
            Phone.isNotBlank() && Address.isNotBlank()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
        OutlinedTextField(
            value = ID,
            onValueChange = { ID = it },
            label = { Text("ID") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
    }
        Row {
            OutlinedTextField(
                value = Name,
                onValueChange = { Name = it },
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
        }
        Row {
            OutlinedTextField(
                value = Phone,
                onValueChange = { Phone = it },
                singleLine = true,
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
        }
        Row {
            OutlinedTextField(
                value = Address,
                onValueChange = { Address = it },
                label = { Text("Address") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )
        }
        Row {
            Button(
                onClick = {
                    val newStudent = Student(
                        id = ID.trim(),
                        name = Name.trim(),
                        phone = Phone.trim(),
                        address = Address.trim()
                    )

                    // 👈 FIX 5: ADD the new user to the shared list
                    students.add(newStudent)
                    ID = ""
                    Name = ""
                    Phone = ""
                    Address = ""
                    onBackToDashboard()

                },
                enabled = isFormValid,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Register and Add to Dashboard")
            }
        }

    }
}