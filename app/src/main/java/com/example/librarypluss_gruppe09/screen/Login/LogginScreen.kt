package com.example.librarypluss_gruppe09.screen.Login

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import com.example.librarypluss_gruppe09.screen.library.LibraryViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librarypluss_gruppe09.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogginScreen(loggedIn: () -> Unit,Signup: () -> Unit,modifier: Modifier = Modifier, viewModel: LoginScreenViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState
    val fieldModifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 4.dp)


    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.errorMessage != 0)
            Text(text = stringResource(id = uiState.errorMessage),
                Modifier.padding(vertical = 8.dp))

        EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)
        PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)

        Row {
            Button(
                onClick = { viewModel.onLoginClick(loggedIn) },
                modifier = Modifier
                    .padding(5.dp, 8.dp),
            ) {
                Text(text = stringResource(R.string.login), fontSize = 16.sp)
            }
            Button(
                onClick = { viewModel.gotosignup(Signup) },
                modifier = Modifier
                    .padding(5.dp, 8.dp),
            ) {
                Text(text = stringResource(R.string.create_account), fontSize = 16.sp)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun signinscreen(loggedIn: () -> Unit,backtologged: () -> Unit,modifier: Modifier = Modifier, viewModel:LoginScreenViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState
    val fieldModifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 4.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.errorMessage != 0)
            Text(text = stringResource(id = uiState.errorMessage),
                Modifier.padding(vertical = 8.dp))

        UsernameField(uiState.username,viewModel::onUserNameChange,fieldModifier)

        EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)

        PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)

        RepeatPasswordField(uiState.repeatPassword, viewModel::onRepeatPasswordChange, fieldModifier)

        Row {
            Button(
                onClick = { viewModel.onSignUpClick(loggedIn) },
                modifier = Modifier
                    .padding(5.dp, 8.dp),
            ) {
                Text(text = stringResource(R.string.create_account), fontSize = 16.sp)
            }
            Button(
                onClick = { viewModel.gotologgin(backtologged)},
                modifier = Modifier
                    .padding(5.dp, 8.dp),
            ) {
                Text(text = stringResource(R.string.backtologin), fontSize = 16.sp)
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(R.string.email)) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email,
                contentDescription = "Email")
        }
    )
}
@Composable
fun UsernameField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(R.string.Username)) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.AccountBox,
                contentDescription = "Username")
        }
    )
}

@Composable
fun PasswordField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    PasswordField(value, R.string.password, onNewValue, modifier)
}

@Composable
fun RepeatPasswordField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    PasswordField(value, R.string.repeat_password, onNewValue, modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField(
    value: String,
    @StringRes placeholder: Int,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }
    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(text = stringResource(placeholder)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "Visibility")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation
    )
}

