package com.example.librarypluss_gruppe09.screen.feed

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Preview
@Composable
fun FeedScreen(modifier: Modifier = Modifier, viewModel: FeedViewModel = hiltViewModel()) {
    val medialist = viewModel.activefeed.collectAsStateWithLifecycle(emptyList())


    val connectionType = remember {
        mutableStateOf("Not Connected")
    }

    Box(modifier = modifier.fillMaxSize()) {
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




