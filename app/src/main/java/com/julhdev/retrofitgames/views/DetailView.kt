package com.julhdev.retrofitgames.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.julhdev.retrofitgames.components.MainTopBar
import com.julhdev.retrofitgames.data.model.SingleGameModel
import com.julhdev.retrofitgames.util.resource.Resource
import com.julhdev.retrofitgames.viewmodel.GamesViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DetailsView(viewmodel: GamesViewModel, navController: NavController, gameId: Int) {

  val gameResource by viewmodel.state.collectAsState(initial = Resource.Loading())

  LaunchedEffect(key1 = gameId) {
    viewmodel.getGameById(gameId)
  }

  Scaffold(
    topBar = {
      MainTopBar(
        title = "Game Details",
        showBackBtn = true,
        onBackClick = { navController.popBackStack() }
      )
    }
  ) { innerPadding ->

    when(val res = gameResource) {
      is Resource.Loading -> {
        DetailsViewContent(innerPadding, gameId)
      }
      is Resource.Success -> {
        val gameDetails = res.data
        DetailsViewContent(innerPadding, gameId, gameDetails)
      }
      is Resource.Error -> {
        DetailsViewContent(innerPadding, gameId)
      }
    }

  }

}


@Composable
fun DetailsViewContent(pad: PaddingValues, gameId: Int, gameDetails: SingleGameModel? = null) {
  Column(
    modifier = Modifier
      .padding(pad)
  ) {
    Text(
      text = "Detalles del juego con nombre: ${gameDetails?.name}",
    )
  }


}