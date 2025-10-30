package com.julhdev.retrofitgames.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.julhdev.retrofitgames.viewmodel.GamesViewModel

@Composable
fun HomeView(viewModel: GamesViewModel) {
  val games by viewModel.games.collectAsState()

  println(games)
  Scaffold { innerPadding ->
    Text(
      text = "Home View",
      modifier = Modifier.padding(innerPadding)
    )
    LazyColumn(
      modifier = Modifier
        .padding(innerPadding)
    ) {
      items(games) { game ->
        Text(text = game.name)
      }
    }
  }
}