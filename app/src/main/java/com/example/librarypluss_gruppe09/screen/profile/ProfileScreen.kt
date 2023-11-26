package com.example.librarypluss_gruppe09.screen.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.librarypluss_gruppe09.NavtoScreen
import com.example.librarypluss_gruppe09.OnScreeen
import com.example.librarypluss_gruppe09.R


@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "User")
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "profile picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Box {
            Text(text = "list of last updated media")
            viewModel.mediaListUpdate()

        }
        Button(onClick = { }) {
            Text(text = "Settings")
        }
        Button(onClick = { }) {
            Text(text = "Statistikk")
        }
        Button(onClick = { navController.navigate(OnScreeen.Goals.name) }) {
            Text(text = "Goals")
        }
    }
}

