package com.julhdev.retrofitgames.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.julhdev.retrofitgames.components.ErrorState
import com.julhdev.retrofitgames.components.LoadingState
import com.julhdev.retrofitgames.components.MainImage
import com.julhdev.retrofitgames.components.MainTopBar
import com.julhdev.retrofitgames.components.MetacriticCard
import com.julhdev.retrofitgames.components.MetacriticWebSite
import com.julhdev.retrofitgames.data.model.SingleGameModel
import com.julhdev.retrofitgames.util.resource.Resource
import com.julhdev.retrofitgames.viewmodel.GamesViewModel

/**
 * Composable que representa la vista de detalles de un juego.
 * @param viewmodel El ViewModel asociado a la vista.
 * @param navController El controlador de navegación de la aplicación.
 * @param gameId El ID del juego para el cual se mostrarán los detalles.
 * @usage DetaillsView(viewmodel, navController, gameId)
 */
@Composable
fun DetailsView(viewmodel: GamesViewModel, navController: NavController, gameId: Int) {
  val gameResource by viewmodel.state.collectAsState(initial = Resource.Loading())

  LaunchedEffect(key1 = gameId) {
    viewmodel.getGameById(gameId)
  }

  Scaffold(
    topBar = {
      MainTopBar(
        title = if (gameResource is Resource.Success) (gameResource as Resource.Success<SingleGameModel>).data.name else "Detalles del juego",
        showBackBtn = true,
        onBackClick = { navController.popBackStack() }
      )
    }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
    ) {
      when (val res = gameResource) {
        is Resource.Loading -> {
          LoadingState()
        }

        is Resource.Success -> {
          val gameDetails = res.data
          DetailsViewContent(gameDetails)
        }

        is Resource.Error -> {
          val errorMessage = res.message
          ErrorState(
            errorMessage = errorMessage
          )
        }
      }
    }
  }
}


@Composable
fun DetailsViewContent(gameDetails: SingleGameModel) {
  MainImage(
    image = gameDetails.backgroundImage,
  )
  Spacer(
    modifier = Modifier
      .height(10.dp)
  )
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 20.dp, end = 5.dp)
  ) {
    MetacriticWebSite(
      url = gameDetails.website,
    )
    MetacriticCard(
      score = gameDetails.metacritic,
    )
  }
}

