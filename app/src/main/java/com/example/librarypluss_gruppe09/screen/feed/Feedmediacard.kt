package com.example.librarypluss_gruppe09.screen.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.librarypluss_gruppe09.models.Feedmedia
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.ui.theme.BlueMoviePrimary
import com.example.librarypluss_gruppe09.ui.theme.Purple80
import com.example.librarypluss_gruppe09.ui.theme.RedGamePrimary
import com.example.librarypluss_gruppe09.ui.theme.YellowBookPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Feedmediacard(Feedmedia: Feedmedia, viewModel: FeedViewModel = hiltViewModel()) {

    var coler = Purple80
    if (Feedmedia.tag == "movie") {
        coler = BlueMoviePrimary
    } else if (Feedmedia.tag == "book") {
        coler = YellowBookPrimary
    } else if (Feedmedia.tag == "game") {
        coler = RedGamePrimary
    }

    Card(
        onClick = {},
        modifier = Modifier.padding(8.dp, 0.dp, 20.dp, 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = coler,
        )
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            AsyncImage(
                model = Feedmedia.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(180.dp)
                    .padding(4.dp, 0.dp, 10.dp, 4.dp),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box {
                        Text(text = Feedmedia.tittle, textAlign = TextAlign.Center)
                    }
                    Box {
                        Text(text = Feedmedia.type, textAlign = TextAlign.Center)
                    }
                    Box {
                        Text(text = Feedmedia.creator, textAlign = TextAlign.Center)
                    }
                    Box {
                        Text(text = Feedmedia.tag, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}