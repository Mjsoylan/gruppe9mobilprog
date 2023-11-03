package com.example.librarypluss_gruppe09//package com.example.librarypluss_gruppe09
//
//@HiltViewModel
//class medieViewModel {
//
//    val movies = storageService.movies
//
//    init {
//        viewModelScope.launch {
//            if (movies.first().isEmpty()) {
//                Datasource.movieList.forEach { movie ->
//                    storageService.save(movie)
//                }
//            }
//        }
//    }
//
//    fun createMovie(movieTitle: String) {
//        viewModelScope.launch {
//            storageService.save(Movie(title = movieTitle))
//        }
//    }
//}