package com.example.librarypluss_gruppe09.screen.goals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
        onClick = {  },
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = coler,
        )

    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            AsyncImage(
                model = media.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(90.dp)
                    .padding(2.dp, 0.dp, 2.dp, 2.dp),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Box {
                        Text(text = media.tittle, textAlign = TextAlign.Center)
                    }
                    Box {
                        Text(text = media.type, textAlign = TextAlign.Center)
                    }
                    Box {
                        Text(text = media.creator, textAlign = TextAlign.Center)
                    }
                    Box {
                        Text(text = media.tag, textAlign = TextAlign.Center)
                    }

                }
                Button(onClick = { viewModel.deleteMediaCard(media)}, Modifier.padding(8.dp).align(Alignment.BottomEnd)) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete media",)
                }
            }
        }
    }
}


//todo delite this check,
@Composable
fun AddCheckBox(mediaId: Media, viewModelGoal: GoalsViewModel = viewModel()) {
    val addCheck = remember {
        mutableStateOf(false)
    }

    Checkbox(checked = addCheck.value, onCheckedChange = {
        addCheck.value = it
//        if(addCheck.value && viewModelGoal.addMediaToLibBool.value)
        viewModelGoal.deleteMediaCard(mediaId)

    })
}