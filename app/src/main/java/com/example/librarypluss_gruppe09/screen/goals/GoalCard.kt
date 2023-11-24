package com.example.librarypluss_gruppe09.screen.goals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.librarypluss_gruppe09.DialogComponent
import com.example.librarypluss_gruppe09.models.Media
import com.example.librarypluss_gruppe09.ui.theme.BlueMoviePrimary
import com.example.librarypluss_gruppe09.ui.theme.Purple80
import com.example.librarypluss_gruppe09.ui.theme.RedGamePrimary
import com.example.librarypluss_gruppe09.ui.theme.YellowBookPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalCard(media: Media, viewModel: GoalsViewModel = hiltViewModel()) {

    var coler = Purple80
    if (media.tag == "movie") {
        coler = BlueMoviePrimary
    } else if (media.tag == "book") {
        coler = YellowBookPrimary
    } else if (media.tag == "game") {
        coler = RedGamePrimary
    }

    Card(
        onClick = { },
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
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize().padding(top = 4.dp)
                    ) {

                        AsyncImage(
                            model = media.imageUrl,
                            contentDescription = "",
                            modifier = Modifier
                                .height(90.dp).fillMaxWidth()
                                .padding(4.dp, 4.dp, 4.dp, 4.dp),
                        )

                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.TopEnd).background(MaterialTheme.colorScheme.tertiaryContainer , MaterialTheme.shapes.medium)
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
                }
            }
        }
        Button(
            onClick = { viewModel.alertDeleteCard.value = true },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete media")
        }
    }

    // https://developer.android.com/jetpack/compose/components/dialog
    when {
        viewModel.alertDeleteCard.value -> {
            DialogComponent(
                onDismissRequest = { viewModel.alertDeleteCard.value = false },
                onConfirmation = {
                    viewModel.alertDeleteCard.value = false
                    viewModel.deleteMediaCard(media, viewModel.dateToday.toString())
                },
                dialogTitle = "Delete media",
                dialogText = " ${media.tittle} move to history",
                icon = Icons.Default.Delete
            )
        }
    }
}