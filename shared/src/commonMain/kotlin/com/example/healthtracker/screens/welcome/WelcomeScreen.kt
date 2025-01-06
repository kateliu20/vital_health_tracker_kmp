package com.example.healthtracker.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healthtracker.HealthComponent
import com.example.healthtracker.data.articles
import com.example.healthtracker.screens.AppScaffold
import health_tracker.shared.generated.resources.Res
import health_tracker.shared.generated.resources.vital
import org.jetbrains.compose.resources.painterResource

@Composable
fun WelcomeScreen(
  component: HealthComponent,
  onNavigateToBloodPressure: () -> Unit,
  onArticleClick: (String) -> Unit,
) {
  AppScaffold(component = component) { innerPadding ->
    Column(
      modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        text = "Welcome to",
        style =
          MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
          ),
      )
      Image(
        painter = painterResource(Res.drawable.vital),
        contentDescription = "Vital Logo",
        modifier = Modifier.fillMaxWidth(),
      )
      Button(onClick = onNavigateToBloodPressure, modifier = Modifier.padding(4.dp).height(40.dp)) {
        Text("Go to Health Dashboard")
      }
      //      Spacer(modifier = Modifier.height(12.dp))

      // Articles Section
      LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize().padding(16.dp),
      ) {
        items(articles) { article ->
          ArticleCard(
            article = article,
            onArticleClick = { articleUrl -> onArticleClick(articleUrl) },
          )
        }
      }
    }
  }
}
