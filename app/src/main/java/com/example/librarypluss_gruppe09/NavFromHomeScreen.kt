package com.example.librarypluss_gruppe09

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore
@Preview
@Composable
fun HomeScreen() {
    HomeSelection()
}

@Preview
@Composable
fun FeedScreen() {
    // Column Composable,
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        // Parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon Composable
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "feed",
            tint = Color(0xFF0F9D58)
        )
        // Text to Display the current Screen
        Text(text = "Feed", color = Color.Black)
    }
}

@Preview
@Composable
fun ProfileScreen() {
    // Column Composable,
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        // parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon Composable
        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = "profile",
            tint = Color(0xFF0F9D58)
        )
        // Text to Display the current Screen
        Text(text = "Profile", color = Color.Black)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddScreen() {
    var tittle by remember { mutableStateOf("") }
    var media by remember { mutableStateOf("") }
    var creater by remember { mutableStateOf("") }
    var user by remember { mutableStateOf("") }
    // Column Composable,
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        // parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon Composable
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "add",
            tint = Color(0xFF0F9D58)
        )
        // Text to Display the current Screen
        Text(text = "Add", color = Color.Black)
        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("user") }
        )
        OutlinedTextField(
            value = tittle,
            onValueChange = { tittle= it  },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("tittle") }
        )
        OutlinedTextField(
            value = media,
            onValueChange = { media = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("media") }
        )
        OutlinedTextField(
                value = creater,
        onValueChange = { creater = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("creater") }
        )

        Button(onClick = { val tempadd = hashMapOf(
            "user" to user,
            "tittle" to tittle,
            "media" to  media,
            "creater" to  creater
        )
            user=""
            tittle=""
            media=""
            creater=""


            db.collection("tempadd")
                .add(tempadd)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }) { Text(text = "add") }
    }
}