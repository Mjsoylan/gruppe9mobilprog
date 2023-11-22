package com.example.librarypluss_gruppe09.screen.library

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.ui.theme.BlueMoviePrimary
import com.example.librarypluss_gruppe09.ui.theme.Purple80
import com.example.librarypluss_gruppe09.ui.theme.RedGamePrimary
import com.example.librarypluss_gruppe09.ui.theme.YellowBookPrimary

//todo move to models

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaCard(media: Media, viewModel: LibraryViewModel = hiltViewModel()) {

    var coler = Purple80
    //todo add boock/ movie and Game icon form Mads branch

    if (media.tag == "movie") {
        coler = BlueMoviePrimary
    } else if (media.tag == "book") {
        coler = YellowBookPrimary
    } else if (media.tag == "game") {
        coler = RedGamePrimary
    }


    Card(
        onClick = {
            //todo add a on of button for when to dele a card
        },
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = coler,
        )
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .height(180.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
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
                    ) {

                        AsyncImage(
                            model = media.imageUrl,
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .height(90.dp)
                                .padding(2.dp, 2.dp, 2.dp, 2.dp),
                        )

                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .align(Alignment.TopEnd)
                        ) {
                            Text(
                                text = media.tag,
                                textAlign = TextAlign.Center,
                                style = TextStyle(),
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

                }
            }
        }
    }
}