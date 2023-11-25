package com.example.librarypluss_gruppe09.screen.feed

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.librarypluss_gruppe09.ui.theme.ConnectionState
import com.example.librarypluss_gruppe09.ui.theme.connectivityState


@Composable
fun FeedScreen(modifier: Modifier = Modifier, viewModel: FeedViewModel = hiltViewModel()) {
    val medialist = viewModel.activefeed.collectAsStateWithLifecycle(emptyList())

//https://medium.com/scalereal/observing-live-connectivity-status-in-jetpack-compose-way-f849ce8431c7
    val networkconection by connectivityState()

    val boolConnection = networkconection === ConnectionState.Available

    //
    val animateNetworkConectionInfo by remember {
        mutableStateOf(true)
    }

    val density = LocalDensity.current

    Box(modifier = modifier.fillMaxSize()) {
        //https://developer.android.com/jetpack/compose/animation/composables-modifiers
//        AnimatedVisibility(visible = animateNetworkConectionInfo, enter = slideInVertically {
//            // Slide in from 40 dp from the top.
//            with(density) { -40.dp.roundToPx() }
//        } + expandVertically(
//            // Expand from the top.
//            expandFrom = Alignment.Top
//        ) + fadeIn(
//            // Fade in with the initial alpha of 0.3f.
//            initialAlpha = 0.3f
//        ),
//            exit = slideOutVertically() + shrinkVertically() + fadeOut()) {

            //composabel
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)){
                Text(text = "connection is on, show UI ${boolConnection}")
            }
//        }
        
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .wrapContentSize(Alignment.TopCenter),
            ) {
                Text(text = "Feed")
            }

            val cont: Context = LocalContext.current

//https://www.geeksforgeeks.org/android-jetpack-compose-display-current-internet-connection-type/
            Thread(Runnable {
                while (true) {

                    // Invoking the Connectivity Manager
//                    val conectivity = cont.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                    val contection = ConnectivityManager.EXTRA_NO_CONNECTIVITY


                    // Fetching the Network Information


                    // on below line finding if connection
                    // type is wifi or mobile data.
//
                //                    for (ni in netInfo.) {
//                        if (ni.typeName.equals("WIFI", ignoreCase = true))
//                            if (ni.isConnected) connectionType.value = "WIFI"
//                        if (ni.typeName.equals("MOBILE", ignoreCase = true))
//                            if (ni.isConnected) connectionType.value = "MOBILE DATA"
//                    }
                }
            }).start()  // Starting the thread

            Log.i("somhai", viewModel.activefeed.toString())
            Log.i("somhai", medialist.toString())

//            LazyVerticalGrid(
//                columns = GridCells.FixedSize(360.dp),
//                content = {
//
//                        items(medialist.value.sortedByDescending {it.uploadtime}, key = { it.mediaId }) { medie ->
//
//                            Feedmediacard(medie)
//
//
//                        }
//                    }, modifier = modifier.padding(20.dp,0.dp,10.dp)
//
//            )
        }
    }
}




