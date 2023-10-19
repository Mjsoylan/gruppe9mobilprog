package com.example.librarypluss_gruppe09

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.librarypluss_gruppe09.ui.theme.LibraryPluss_Gruppe09Theme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavtoScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
    ) { innerPadding ->
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
        }
    }
}

fun searchBooks(query: String) {
    val booksRepository = BooksRepository()

    val call = booksRepository.searchBooks(query)
    call.enqueue(object : Callback<BookResponse> {
        override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
            if (response.isSuccessful && response.body() != null) {
                val booksList = response.body()!!.items
                Log.d("BOOKS_LOG", "Books List: $booksList")
            } else {
                Log.d("API_RESPONSE", "Error: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<BookResponse>, t: Throwable) {
            Log.e("API_FAILURE", "Error: ${t.localizedMessage}")
        }
    })
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LibraryPluss_Gruppe09Theme {
        NavtoScreen()
    }
}