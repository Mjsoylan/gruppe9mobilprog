package com.example.librarypluss_gruppe09.screen.Login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarypluss_gruppe09.R
import com.example.librarypluss_gruppe09.commonext.isValidEmail
import com.example.librarypluss_gruppe09.commonext.isValidPassword
import com.example.librarypluss_gruppe09.firebaseservice.AccountService
import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject
constructor(private val account: AccountService) :
    ViewModel() {

    val user = account.currentUser
    var uiState = mutableStateOf(LogginUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password


    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onLoginClick(loggedIn: () -> Unit) {
        if (!email.isValidEmail()) {
            uiState.value = uiState.value.copy(errorMessage = R.string.email_error)
            return
        }

        else if (!password.isValidPassword()) {
            uiState.value = uiState.value.copy(errorMessage = R.string.password_error)
            return
        }

        viewModelScope.launch {
            try {
                account.authenticate(email, password) { error ->
                    if (error == null)
                        loggedIn()
                }
            }
            catch(e: Exception) {
                uiState.value = uiState.value.copy(errorMessage = R.string.could_not_log_in)
            }
        }
    }
        fun onSignUpClick(loggedIn: () -> Unit) {
            if (!email.isValidEmail()) {
                uiState.value = uiState.value.copy(errorMessage = R.string.email_error)
                return
            } else if (!password.isValidPassword()) {
                uiState.value = uiState.value.copy(errorMessage = R.string.password_error)
                return
            } else if (!(password == uiState.value.repeatPassword)) {
                uiState.value = uiState.value.copy(errorMessage = R.string.password_match_error)
                return
            }

            viewModelScope.launch {
                try {
                    account.createaccunt(email, password) { error ->
                        if (error == null)
                            uiState.value = uiState.value.copy(errorMessage = R.string.Accunt_Created)
                    }
                }
                catch(e: Exception) {
                    uiState.value = uiState.value.copy(errorMessage = R.string.Accunt_Created_failed)
                }
            }
        }
          fun fastLogin(loggedIn: () -> Unit) {
              viewModelScope.launch {
                  account.fastloggin()
                  loggedIn()
              }

            }

    }




