package com.example.librarypluss_gruppe09
data class GameResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Game>
)

data class Game(
    val gameInfo: GameInfo
)

data class GameInfo(
    val id: Int,
    val title: String,
    val developer: List<String>
)