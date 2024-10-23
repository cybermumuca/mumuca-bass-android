package com.mumuca.musicplayer.ui.collection

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mumuca.musicplayer.data.local.models.Track

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen(
    albumName: String,
    artistName: String,
    collectionCover: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = albumName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Row(modifier = Modifier.padding(bottom = 16.dp)) {
                    AsyncImage(
                        model = collectionCover,
                        contentDescription = null,
                        modifier = Modifier.size(128.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = albumName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Text(text = artistName, fontSize = 16.sp, color = Color.Gray)
                    }
                }
                LazyColumn {
                    val trackList = listOf<Track>(
                        Track(Uri.EMPTY, "Track 1", 1, "Artist 1", "data1", 180, "Title 1"),
                        Track(Uri.EMPTY, "Track 2", 2, "Artist 2", "data2", 200, "Title 2"),
                        Track(Uri.EMPTY, "Track 3", 3, "Artist 3", "data3", 240, "Title 3")
                    )
                    items(trackList.size) { trackIndex ->
                        TrackItem(trackList[trackIndex])
                    }
                }
            }
        }
    )
}

@Composable
fun TrackItem(track: Track) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = track.title, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        Text(text = track.artist, fontSize = 14.sp, color = Color.Gray)
        Text(text = track.duration.toString(), fontSize = 12.sp, color = Color.Gray)
    }
}

@Preview
@Composable
fun CollectionScreenPreview() {
    CollectionScreen(
        albumName = "Album Name",
        artistName = "Artist Name",
        collectionCover = "https://picsum.photos/200/300"
    )
}