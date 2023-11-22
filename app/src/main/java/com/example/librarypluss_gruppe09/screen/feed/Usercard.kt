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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.librarypluss_gruppe09.firebaseservice.User
import com.example.librarypluss_gruppe09.models.UserAccunt
import com.example.librarypluss_gruppe09.ui.theme.Purple80


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Usercard(User: UserAccunt, viewModel: FeedViewModel = hiltViewModel()) {

    var coler = Purple80

    Card(
        onClick = {
            //todo add a on of button for when to dele a card
        },
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = coler,
        )
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
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
                        Text(text = User.userid, textAlign = TextAlign.Center)
                    }
                    Box {
                        Text(text = User.username, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}