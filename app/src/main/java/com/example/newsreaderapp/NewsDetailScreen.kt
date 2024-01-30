package com.example.newsreaderapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter
import com.example.domain.model.News

@Composable
fun NewsDetailScreen(
    news: News
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = news.title,
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        Image(
            painter = rememberGlidePainter(news.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = news.description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Read more",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.clickable {
                val uri = Uri.parse(news.url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }
        )
    }
}
