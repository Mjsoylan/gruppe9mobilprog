package com.example.librarypluss_gruppe09


import androidx.compose.foundation.Image
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



@Composable
fun Profile(){
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "user name")
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "profile picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Button(onClick = { }) {
            Text(text = "Settings")
        }
        Button(onClick = { }) {
            Text(text = "Statestikk")
        }
        Button(onClick = { }) {
            Text(text = "Goals")
        }

    }

}

