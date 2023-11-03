package com.example.librarypluss_gruppe09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.librarypluss_gruppe09.ui.theme.LibraryPluss_Gruppe09Theme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            LibraryPluss_Gruppe09Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavtoScreen()
                }
            }
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavtoScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(topBar = {
        TopAppBar(
            title = { /*TODO*/ },
            actions = {
                Button(onClick = { navController.navigate("login") }) {
                    Text(text = "login")
                }
                Button(onClick = { navController.navigate("SiginUp") }) {
                    Text(text = "signup")
                }
            }
        )
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(goto = { navController.navigate("home") }, Modifier.fillMaxSize().padding())
            }
            composable("home") {
                BottomNavigation()
            }
            composable("SiginUp") {
                SiginUp(goto = { navController.navigate("home") }, Modifier.fillMaxSize().padding())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LibraryPluss_Gruppe09Theme {
        NavtoScreen()
    }
}