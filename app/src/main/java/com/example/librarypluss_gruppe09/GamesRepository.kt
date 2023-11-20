package com.example.librarypluss_gruppe09

import retrofit2.Call

/*
class GamesRepository(private val apiService: GamesApiService) {

    fun searchGames(query: String): Call<List<GameResponse>> {
        return apiService.searchGames("name,rating,cover.url", query)
    }
}
*/

class GamesRepository {

    fun searchGames(query: String): Call<GameResponse> {
        val gameQuery = GameQuery(fields = "name", search = query)
        return RetrofitClientGames.instance.searchGames(gameQuery)
    }
}

