package com.julhdev.retrofitgames.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.julhdev.retrofitgames.components.MainTopBar
import com.julhdev.retrofitgames.data.model.GameList
import com.julhdev.retrofitgames.util.resource.Resource
import com.julhdev.retrofitgames.viewmodel.GamesViewModel

@Composable
fun HomeView(viewModel: GamesViewModel) {

  Scaffold(
    topBar = {
      MainTopBar(
        title = "Games List"
      )
    }
  ) { innerPadding ->
    HomeViewContent(viewModel, innerPadding)
  }
}
@Composable
fun HomeViewContent(viewModel: GamesViewModel, pad: PaddingValues) {
  val gamesResource by viewModel.games.collectAsState()

  when (gamesResource) {
    is Resource.Loading -> {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(pad),
        contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator()
      }
    }
    is Resource.Success -> {
      val gamesList = (gamesResource as Resource.Success<List<GameList>>).data
      if (gamesList.isNotEmpty()) {
        LazyColumn(
          modifier = Modifier.padding(pad)
        ) {
          items(gamesList) { game ->
            Text(text = game.name)
          }
        }
      } else {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .padding(pad),
          contentAlignment = Alignment.Center
        ) {
          Text(text = "No hay juegos disponibles.")
        }
      }
    }
    is Resource.Error -> {
      val errorMessage = (gamesResource as Resource.Error<List<GameList>>).message
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(pad),
        contentAlignment = Alignment.Center
      ) {
        Text(text ="$errorMessage")
      }
    }
  }
}