package com.julhdev.retrofitgames.data.state

import com.julhdev.retrofitgames.data.model.SingleGameModel

data class GameState(
  val game: SingleGameModel = SingleGameModel(
    name = "N/A",
    description = "N/A",
    metacritic = 0,
    website = "N/A",
    backgroundImage = "N/A"
  ),
)