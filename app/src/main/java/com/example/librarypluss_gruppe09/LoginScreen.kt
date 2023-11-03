package com.example.librarypluss_gruppe09

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase


lateinit var auth: FirebaseAuth
//public val user = TODO();

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(goto: () -> Unit,
    modifier: Modifier = Modifier)  {
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

        Button(onClick = {signin(email,password) ; goto() }) {
            Text(text = "logg in")
        }
        Button(onClick = {createAccount(email,password); goto()   }) {
            Text(text = "create account")
        }
        Button(onClick = {signin("mads.soyland@gmail.com","password") ; goto() }) {
            Text(text = "skip login (temp)")
        }
        
    }

}

private fun createAccount(email: String, password: String) {
    auth = Firebase.auth
    auth.createUserWithEmailAndPassword(email, password)
    signin(email,password)
    val user = Firebase.auth.currentUser

    val profileUpdates = userProfileChangeRequest {
        displayName = "testusername"
    }
    user!!.updateProfile(profileUpdates)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "User profile updated.")
            }
        }
        }
private fun signin(email: String,password: String) {
    auth = Firebase.auth
    auth.signInWithEmailAndPassword(email, password)
}
