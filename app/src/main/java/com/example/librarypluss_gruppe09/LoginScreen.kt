package com.example.librarypluss_gruppe09

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


lateinit var auth: FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(goto: () -> Unit,
    modifier: Modifier = Modifier
) {
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(value = email, onValueChange = {email=it},label = { Text("email") })
        OutlinedTextField(
            value = password,
            onValueChange = { password = it},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text("password") }
        )
        Button(onClick = { goto() }) {
            Text(text = "link to Home")
        }
        
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiginUp(goto: () -> Unit,
            modifier: Modifier = Modifier) {


    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("email") })
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text("password") }
        )
        Button(onClick = { goto() }) {
            Text(text = "link to Home")

        }
        Button(onClick = {createAccount(email,password)  }) {
            Text(text = "link to Home")


        }
    }
}

private fun createAccount(email: String, password: String) {
    auth = Firebase.auth
    // [START create_user_with_email]
    auth.createUserWithEmailAndPassword(email, password)

        }
    // [END create_user_with_email]
