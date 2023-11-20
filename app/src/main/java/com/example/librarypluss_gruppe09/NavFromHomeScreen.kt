package com.example.librarypluss_gruppe09

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.librarypluss_gruppe09.firebaseservice.AccountService
import com.example.librarypluss_gruppe09.models.addmediabook
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddScreen(modifier : Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(topBar = {
        TopAppBar(
            title = { /*TODO*/ },
            actions = {
                IconButton(onClick = { navController.navigate("books") }) {
                    Icon(
                        Icons.Filled.List,
                        contentDescription = "books"
                    )
                }
                IconButton(onClick = { navController.navigate("movies") }) {
                    Icon(
                        Icons.Filled.Done,
                        contentDescription = "movies"
                    )
                }
                IconButton(onClick = { navController.navigate("games") }) {
                    Icon(
                        Icons.Filled.AccountBox,
                        contentDescription = "games"
                    )
                }
            }
        )
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "books",
            modifier = modifier.padding(innerPadding)
        ) {
            composable("books") {
                Addbookscreen()
            }
            composable("movies") {
                Addmoviescreen()
            }
            composable("games") {
                Addgamescreen()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Addbookscreen(){
    var search by remember { mutableStateOf("") }
    var booksList by remember { mutableStateOf(listOf(Book(BookInfo("1", "Sample Book", listOf("Author"))))) }
    var tittle by remember { mutableStateOf("") }
    var booktype by remember { mutableStateOf("") }
    var pagenum by remember { mutableStateOf("") }
    var creater by remember { mutableStateOf("") }
    val user  = FirebaseAuth.getInstance().currentUser!!.uid
    var review by remember { mutableStateOf("") }



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
        Text(text = "Add books", color = Color.Black)
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("search") }
        )

        Button(
            onClick = {
                // Trigger the API call here
                searchBooks(search) // 'search.value' gets the current text from the 'search' state
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Search")
        }

        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(booksList) { book ->
                println("Rendering book: ${book.volumeInfo.title}")
                BookItem(book)
            }
        }

        OutlinedTextField(
            value = tittle,
            onValueChange = { tittle= it  },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("tittle") }
        )
        OutlinedTextField(
            value = booktype,
            onValueChange = { booktype = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("booktype") }
        )
        OutlinedTextField(
            value = pagenum,
            onValueChange = { pagenum = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("pagenumber") }
        )
        OutlinedTextField(
            value = creater,
            onValueChange = { creater = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("creater") }
        )
        OutlinedTextField(
                value = review,
        onValueChange = { review = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text("review (optional)") }
        )

        Button(onClick = {
            val books = hashMapOf(
            "tittle" to tittle,
            "booktype" to booktype,
            "pagenum" to pagenum,
            "creater" to creater
        )
            val reviewer = hashMapOf(
                "text" to review
            )

            if (review != "") {
                db.collection("user").document(user).collection("addedMedia").add(books).addOnSuccessListener {documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                        db.collection("user/${user}/addedMedia/${documentReference.id}/review").add(reviewer)

                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }
            else{
                db.collection("user").document(user).collection("addedMedia").add(books).addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot added with ID: ${user}")
                }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }

            tittle=""
            booktype=""
            creater=""
            pagenum=""
            review=""

        }) { Text(text = "add") }
    }
}

@Composable
fun BookItem(book: Book) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = book.volumeInfo.title)
        Button(onClick = { /* Do something in the future */ }) {
            Text(text = "+")
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Addmoviescreen(){
    var tittle by remember { mutableStateOf("") }
    var movietype by remember { mutableStateOf("") }
    var creater by remember { mutableStateOf("") }
    val user = FirebaseAuth.getInstance().currentUser!!.uid
    var review by remember { mutableStateOf("") }

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
        Text(text = "Add movie", color = Color.Black)
        OutlinedTextField(
            value = tittle,
            onValueChange = { tittle= it  },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("tittle") }
        )
        OutlinedTextField(
            value = movietype,
            onValueChange = { movietype = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("movietype") }
        )
        OutlinedTextField(
            value = creater,
            onValueChange = { creater = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("creater") }
        )
        OutlinedTextField(
            value = review,
            onValueChange = { review = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("review (optional)") }
        )

        Button(onClick = { val movies = hashMapOf(
            "tittle" to tittle,
            "movietype" to  movietype,
            "creater" to  creater
        )
            val reviewer = hashMapOf(
                "text" to review
            )


            if (review != "") {
                db.collection("user").document(user).collection("addedMedia").add(movies).addOnSuccessListener {documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                    db.collection("user/${user}/addedMedia/${documentReference.id}/review").add(reviewer)

                }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }
            else{
                db.collection("user").document(user).collection("addedMedia").add(movies).addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot added with ID: ${user}")
                }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }
            tittle=""
            movietype=""
            creater=""
            review=""
        }) { Text(text = "add") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Addgamescreen(){
    var tittle by remember { mutableStateOf("") }
    var gametype by remember { mutableStateOf("") }
    var creater by remember { mutableStateOf("") }
    val user = FirebaseAuth.getInstance().currentUser!!.uid
    var review by remember { mutableStateOf("") }
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
        Text(text = "add games", color = Color.Black)
        OutlinedTextField(
            value = tittle,
            onValueChange = { tittle= it  },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("tittle") }
        )
        OutlinedTextField(
            value = gametype,
            onValueChange = { gametype = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("gametype") }
        )
        OutlinedTextField(
            value = creater,
            onValueChange = { creater = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("creater") }
        )
        OutlinedTextField(
            value = review,
            onValueChange = { review = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("review (optional)") }
        )

        Button(onClick = { val games = hashMapOf(
            "tittle" to tittle,
            "gametype" to  gametype,
            "creater" to  creater
        )
            val reviewer = hashMapOf(
                "text" to review
            )



            if (review != "") {
                db.collection("user").document(user).collection("addedMedia").add(games).addOnSuccessListener {documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                    db.collection("user/${user}/addedMedia/${documentReference.id}/review").add(reviewer)

                }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }
            else{
                db.collection("user").document(user).collection("addedMedia").add(games).addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot added with ID: ${user}")
                }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }

            tittle=""
            gametype=""
            creater=""
            review=""
        }) { Text(text = "add") }
    }
}