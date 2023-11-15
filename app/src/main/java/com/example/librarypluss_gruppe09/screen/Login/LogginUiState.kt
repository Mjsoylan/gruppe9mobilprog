package com.example.librarypluss_gruppe09.screen.Login

import androidx.annotation.StringRes

data class  LogginUiState (
        val email: String = "",
        val password: String = "",
        val repeatPassword: String = "",
        @StringRes val errorMessage: Int = 0
)