package com.example.librarypluss_gruppe09

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.librarypluss_gruppe09.screen.goals.GoalsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalCard(media: Media, viewModel: GoalsViewModel = hiltViewModel()) {
    Card(
        onClick = { viewModel.addCardToLibrary(media)
                  },
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)

        ) {
        Box {
            AddCheckBox(media)
        }

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
                }
            }
        }

}

@Composable
fun AddCheckBox(mediaId : Media, viewModelGoal: GoalsViewModel = viewModel()) {
    val addCheck = remember {
        mutableStateOf(false)
    }

    Checkbox(checked = addCheck.value, onCheckedChange = {
        addCheck.value = it
        if(addCheck.value && viewModelGoal.addMediaToLibBool.value)
            viewModelGoal.addCardToLibrary(mediaId)
    })
}