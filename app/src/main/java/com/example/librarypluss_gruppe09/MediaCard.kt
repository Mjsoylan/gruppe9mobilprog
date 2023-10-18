package com.example.librarypluss_gruppe09

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.librarypluss_gruppe09.models.Media

//todo move to models


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaCard(media : Media) {
    var tittle by remember { mutableStateOf("tittle") }
    var booktype by remember { mutableStateOf("booktype") }
    var pagenum by remember { mutableStateOf("x3") }
    var creater by remember { mutableStateOf("x4") }
    var user by remember { mutableStateOf("x5") }

    Card(
        onClick = { },
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "library",
                        tint = Color(0xFF0F9D58),
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Box {
                    Text(text = media.tittle, textAlign = TextAlign.Center)
                }
                Box {
                    Text(text = "booktype", textAlign = TextAlign.Center)
                }
                Box {
                    Text(text = "pagenum", textAlign = TextAlign.Center)
                }
                Box {
                    Text(text = "creater", textAlign = TextAlign.Center)
                }
                //                    Box() {
//                        Text(text = medie.title, textAlign = TextAlign.Center,)
//                    }
//                    Box() {
//                        Text(text = medie.booktype, textAlign = TextAlign.Center,)
//                    }
//                    Box() {
//                        Text(text =medie.pagenum, textAlign = TextAlign.Center,)
//                    }
//                    Box() {
//                        Text(text = medie.creater, textAlign = TextAlign.Center,)
//                    }
            }
        }
    }
}