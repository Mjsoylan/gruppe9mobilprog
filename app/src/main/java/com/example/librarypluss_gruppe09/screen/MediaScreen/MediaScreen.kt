package com.example.librarypluss_gruppe09.screen.MediaScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun MediaScreen(navController: NavHostController, viewModel: MediaViewModel = hiltViewModel()) {

    val media by viewModel.media

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        )
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .padding(top = 4.dp)
            ) {

                AsyncImage(
                    model = media.imageUrl,
                    contentDescription = "",
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .padding(4.dp, 4.dp, 4.dp, 4.dp),
                )

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd)
                        .background(
                            MaterialTheme.colorScheme.tertiaryContainer,
                            MaterialTheme.shapes.medium
                        )
                ) {
                    Text(
                        text = media.tag,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(5.dp),
                    )
                }
            }

            Box {
                Text(
                    text = media.tittle,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Box {
                Text(
                    text = media.type,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Box {
                Text(
                    text = media.creator,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "Back to Library")
            }

        }
    }
}