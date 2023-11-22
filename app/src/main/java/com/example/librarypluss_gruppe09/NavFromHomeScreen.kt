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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.librarypluss_gruppe09.models.Media
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
fun AddScreen(modifier: Modifier = Modifier) {
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
@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun Addbookscreen() {
    var search by remember { mutableStateOf("") }
    var booksList by remember {
        mutableStateOf(
            listOf(
                Book(
                    BookInfo(
                        "1", "Sample Book", listOf("Authors"), 100,
                        listOf("Categories"), ImageLinks("")
                    )
                )
            )
        )
    }

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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
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
    }
}

@Composable
fun BookItem(book: Book) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).wrapContentSize(Alignment.Center),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = book.volumeInfo.title, modifier = Modifier.width(200.dp))
        Button(onClick = {
            //TODO CLEAN AND MAKE BEAUTIFUL


            var genre = ""
            var author = ""
            var genreToDelete = ""
            var authorToDelete = ""
            val charToDelete1 = '['
            val charToDelete2 = ']'
            if (book.volumeInfo.categories.isNotEmpty() == true) {
                genreToDelete = book.volumeInfo.categories.toString()
                val modifiedGenre = genreToDelete.replace(charToDelete1.toString(), "")
                genre = modifiedGenre.replace(charToDelete2.toString(), "")
            }

            if (book.volumeInfo.authors.isNotEmpty() == true) {
                authorToDelete = book.volumeInfo.authors.toString()
                val modifiedAuthor = authorToDelete.replace(charToDelete1.toString(), "")
                author = modifiedAuthor.replace(charToDelete2.toString(), "")
            }

            val books = Media(
                "",
                book.volumeInfo.title,
                author,
                genre,
                "book",
                book.volumeInfo.imageLinks?.smallThumbnail.toString()
            )
            upload(books)
        }) {
            Text("+") // This is the content for the Button.
        }
        Button(onClick = {
            //TODO CLEAN AND MAKE BEAUTIFUL


            var genre = ""
            var author = ""
            var genreToDelete = ""
            var authorToDelete = ""
            val charToDelete1 = '['
            val charToDelete2 = ']'
            if (book.volumeInfo.categories.isNotEmpty() == true) {
                genreToDelete = book.volumeInfo.categories.toString()
                val modifiedGenre = genreToDelete.replace(charToDelete1.toString(), "")
                genre = modifiedGenre.replace(charToDelete2.toString(), "")
            }

            if (book.volumeInfo.authors.isNotEmpty() == true) {
                authorToDelete = book.volumeInfo.authors.toString()
                val modifiedAuthor = authorToDelete.replace(charToDelete1.toString(), "")
                author = modifiedAuthor.replace(charToDelete2.toString(), "")
            }

            val books = Media(
                "",
                book.volumeInfo.title,
                author,
                genre,
                "book",
                book.volumeInfo.imageLinks?.smallThumbnail.toString()
            )
            uploadToGoal(books)
        }) {
            Text("+ goal") // This is the content for the Button.
        }
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Addmoviescreen() {
    var search by remember { mutableStateOf("") }
    var moviesList by remember { mutableStateOf(listOf(Movie(1, "Sample Movie", ""))) }

    fun searchMovies(searchQuery: String) {
        val movieApi = retrofitMovies.create(MoviesApiService::class.java)

        movieApi.searchMovies(
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkY2E5Y2FmNmIyNGYwMjJhMDdkN2VjNDg5Yzc5YzQ5MiIsInN1YiI6IjY1NTc5NGZkN2YwNTQwMThkNmYzMjYwNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FLq5ehkpOZaVpfY9xWWKtCH4arc7bVk_uf0CS_R8aeI",
            searchQuery
        ).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movieResponseList = response.body()?.results ?: emptyList()
                    moviesList = movieResponseList
                    Log.d("MOVIES_LOG", "Response successful: $moviesList")
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
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
                println("Rendering movie: ${movie.title}")
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
            .padding(8.dp).wrapContentSize(Alignment.Center),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = movie.title, modifier = Modifier.width(200.dp))
        Button(onClick = {
            val poster = "https://image.tmdb.org/t/p/original" + movie.poster_path
            val movies = Media("", movie.title, "", "", "movie", poster)
            upload(movies)
        }) {
            Text("+") // This is the content for the Button.
        }
        Button(onClick = {
            val poster = "https://image.tmdb.org/t/p/original" + movie.poster_path
            val movies = Media("", movie.title, "", "", "movie", poster)
            uploadToGoal(movies)
        }) {
            Text("+ goal") // This is the content for the Button.
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Addgamescreen() {
    var search by remember { mutableStateOf("") }
    var gamesList by remember {
        mutableStateOf(
            listOf(
                Media(
                    "",
                    "Sample Game",
                    "",
                    "",
                    "game"/*, listOf(1,2), listOf("involved_companies")*/
                )
            )
        )
    }

    fun searchGames(searchQuery: String) {
        val gameApi = retrofitGames.create(GamesApiService::class.java)
        val query = "fields id, name, genres, involved_companies; search \"$searchQuery\";"
        val call = gameApi.searchGames(
            "35nfm0jkloxrrfi54afigm9qklpuhq",
            "Bearer 2cz8jk3istcu7y6ingfwnh7529lfed", query
        )

        call.enqueue(object : Callback<List<GameResponse>> {
            override fun onResponse(
                call: Call<List<GameResponse>>,
                response: Response<List<GameResponse>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val gamesResponseList = response.body()
                    val gamesConvertedList: List<Media> = gamesResponseList?.map { gameResponse ->
                        // Assuming GameResponse has the same 'id' and 'name' properties as Game
                        Media(
                            tittle = gameResponse.name ?: "Unknown",
                            tag = "game"/*, genres = gameResponse.genres, involved_companies = gameResponse.involved_companies*/
                        )
                    } ?: listOf()
                    gamesList = gamesConvertedList
                    Log.d("GAMES_LOG", "Response successful: $gamesList")
                } else {
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
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
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            items(gamesList) { media ->
                println("Rendering book: ${media.tittle}")
                GameItem(media)
            }
        }
    }
}

@Composable
fun GameItem(game: Media) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).wrapContentSize(Alignment.Center),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = game.tittle, modifier = Modifier.width(200.dp))
        Button(onClick = {
            upload(game)
        }, modifier = Modifier.padding(2.dp)) {
            Text("+") // This is the content for the Button.
        }
        Button(onClick = {
            uploadToGoal(game)
        }, modifier = Modifier.padding(2.dp)) {
            Text("+ goal") // This is the content for the Button.
        }
    }
}

fun upload(media: Media) {
    val user = FirebaseAuth.getInstance().currentUser!!.uid
    db.collection("user").document(user).collection("addedMedia").add(media)
        .addOnSuccessListener {
            Log.d(TAG, "DocumentSnapshot added with ID: ${user}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding document", e)
        }
}

fun uploadToGoal(media: Media) {
    val user = FirebaseAuth.getInstance().currentUser!!.uid
    db.collection("user").document(user).collection("mediagoal").add(media)
        .addOnSuccessListener {
            Log.d(TAG, "DocumentSnapshot added with ID: ${user}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding document", e)
        }
}
