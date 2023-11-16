package com.example.librarypluss_gruppe09.screen.editcard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("SuspiciousIndentation")
@Preview
@Composable
fun EditCardScreen(
    modifier: Modifier = Modifier, viewModel: EditCardViewModel = hiltViewModel()
) {

    val edit by viewModel.editcard

//    val navController = rememberNavController()

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()) {
            Text(text = edit.description)


//            Button(onClick = { navController.navigate(route = GOALS_LIST) }) {
//                Text(text = "back")
//            }

    }

}