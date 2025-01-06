package com.example.healthtracker.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.healthtracker.data.Article
import org.jetbrains.compose.resources.painterResource

@Composable
fun ArticleCard(article: Article, onArticleClick: (String) -> Unit) {
  Card(
    modifier = Modifier.fillMaxWidth().aspectRatio(1f).clickable { onArticleClick(article.url) },
    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.elevatedCardElevation(),
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      Image(
        painter = painterResource(article.imageRes),
        contentDescription = article.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize(),
      )
      Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(
          text = article.title,
          style =
            MaterialTheme.typography.bodyLarge.copy(
              fontWeight = FontWeight.Bold,
              color = Color.Black,
            ),
          modifier = Modifier.padding(10.dp),
          textAlign = TextAlign.Center,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis,
        )
      }
    }
  }
}
