package com.example.librarypluss_gruppe09

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
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//val db = Firebase.firestore
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

//@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Addbookscreen(){
    var search by remember { mutableStateOf("") }
    var booksList by remember { mutableStateOf(listOf(Book(BookInfo("1", "Sample Book", listOf("Author"))))) }

    fun searchBooks(query: String) {
        val booksRepository = BooksRepository()

        val call = booksRepository.searchBooks(query)
        call.enqueue(object : Callback<BookResponse> {
            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val fetchedBooks = response.body()!!.items
                    booksList = fetchedBooks
                    Log.d("BOOKS_LOG", "Books List: $booksList")
                } else {
                    Log.d("BOOKS_API_RESPONSE", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                Log.e("BOOKS_API_FAILURE", "Error: ${t.localizedMessage}")
            }
        })
    }

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
/*
        //Upload to FireBase
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
            "user" to user,
            "tittle" to tittle,
            "booktype" to  booktype,
            "pagenum" to pagenum,
            "creater" to  creater
        )
            val reviewer = hashMapOf(
                "user" to user,
                "text" to review
            )

            db.collection("books")
                .add(books)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                        db.collection("books/${documentReference.id}/review").add(reviewer)


                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

            user=""
            tittle=""
            booktype=""
            creater=""
            pagenum=""
            review=""

        }) { Text(text = "add") } */
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



//@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Addmoviescreen(){
    var search by remember { mutableStateOf("") }
    var moviesList by remember { mutableStateOf(listOf(Movie(MovieInfo("1", "Sample Movie", listOf("Author"))))) }

    fun searchMovies(query: String) {
        val moviesRepository = MoviesRepository()

        val call = moviesRepository.searchMovies(query)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val fetchedMovies = response.body()!!.items
                    moviesList = fetchedMovies
                    Log.d("MOVIES_LOG", "Movies List: $moviesList")
                } else {
                    Log.d("MOVIES_API_RESPONSE", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MOVIES_API_FAILURE", "Error: ${t.localizedMessage}")
            }
        })
    }

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
        Text(text = "Add movies", color = Color.Black)
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("search") }
        )

        Button(
            onClick = {
                // Trigger the API call here
                searchMovies(search) // 'search.value' gets the current text from the 'search' state
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Search")
        }

        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(moviesList) { movie ->
                println("Rendering movie: ${movie.volumeInfo.title}")
                MovieItem(movie)
            }
        }
        /*
        //Upload to Firebase
        Text(text = "Add movie", color = Color.Black)
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
            "user" to user,
            "tittle" to tittle,
            "movietype" to  movietype,
            "creater" to  creater
        )
            val reviewer = hashMapOf(
                "user" to user,
                "text" to review
            )

            user=""
            tittle=""
            movietype=""
            creater=""


            db.collection("movies")
                .add(movies)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                        db.collection("books/${documentReference.id}/review").add(reviewer)

                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }) { Text(text = "add") }

         */
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = movie.volumeInfo.title)
        Button(onClick = { /* Do something in the future */ }) {
            Text(text = "+")
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Addgamescreen(){
    var search by remember { mutableStateOf("") }
    var gamesList by remember { mutableStateOf(listOf(Game(1, "Sample Game"))) }

    fun searchGames(searchQuery: String) {
        val gameApi = retrofitGames.create(GamesApiService::class.java)
        val query = "fields id, name; search \"$searchQuery\";"
        val call = gameApi.searchGames("35nfm0jkloxrrfi54afigm9qklpuhq",
            "Bearer 2cz8jk3istcu7y6ingfwnh7529lfed", query)

        call.enqueue(object : Callback<List<GameResponse>> {
            override fun onResponse(call: Call<List<GameResponse>>, response: Response<List<GameResponse>>) {
                if (response.isSuccessful && response.body() != null) {
                    val gamesResponseList = response.body()
                    val gamesConvertedList: List<Game> = gamesResponseList?.map { gameResponse ->
                        // Assuming GameResponse has the same 'id' and 'name' properties as Game
                        Game(id = gameResponse.id, name = gameResponse.name ?: "Unknown")
                    } ?: listOf()
                    gamesList = gamesConvertedList
                    Log.d("GAMES_LOG", "Response successful: $gamesList")
                    }
                else {
                    Log.d("GAMES_API_RESPONSE", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<GameResponse>>, t: Throwable) {
                Log.e("GAMES_API_FAILURE", "Error: ${t.localizedMessage}")
            }
        })
    }

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
        Text(text = "Add games", color = Color.Black)
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("search") }
        )

        Button(
            onClick = {
                // Trigger the API call here
                searchGames(search) // 'search.value' gets the current text from the 'search' state
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Search")
        }

        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(gamesList) { game ->
                println("Rendering book: ${game.name}")
                GameItem(game)
            }
        }


/*
    //Upload to Firebase
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
            "user" to user,
            "tittle" to tittle,
            "gametype" to  gametype,
            "creater" to  creater
        )
            val reviewer = hashMapOf(
                "user" to user,
                "text" to review
            )



            db.collection("games")
                .add(games)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        db.collection("games/${documentReference.id}/review").add(reviewer)

                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

            user=""
            tittle=""
            gametype=""
            creater=""
            review=""
        }) { Text(text = "add") }

 */
    }
}

@Composable
fun GameItem(game: Game) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = game.name)
        Button(onClick = { /* Do something in the future */ }) {
            Text(text = "+")
        }
    }
}