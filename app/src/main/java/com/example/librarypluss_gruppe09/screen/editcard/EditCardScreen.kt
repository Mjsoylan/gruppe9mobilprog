package com.example.librarypluss_gruppe09.screen.editcard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.librarypluss_gruppe09.models.SettDescriptionGoal

@SuppressLint("SuspiciousIndentation")
@Composable
fun EditCardScreen(
    navController: NavController,
    modifier: Modifier = Modifier, viewModel: EditCardViewModel = hiltViewModel()
) {

    val edit by viewModel.editcard

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {

        OutlinedTextField(
            value = viewModel.editFild.value,
            onValueChange = { viewModel.editFild.value = it },
            label = { Text("") })

        //to update a goal
        Button(onClick = {
            viewModel.updateFild(edit, viewModel.editFild.value)
            navController.popBackStack()
        }) {
            Text(text = "Sett goal")
        }

        //when adding a new goal
        Button(onClick = {
            viewModel.addGoal(SettDescriptionGoal(description = viewModel.editFild.value))
            navController.popBackStack()
        }) {
            Text(text = "Add goal")
        }
    }
}