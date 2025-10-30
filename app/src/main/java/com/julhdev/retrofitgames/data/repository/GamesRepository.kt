package com.julhdev.retrofitgames.data.repository

import com.julhdev.retrofitgames.data.api.GamesApi
import com.julhdev.retrofitgames.data.model.GameList
import com.julhdev.retrofitgames.util.resource.Resource
import com.julhdev.retrofitgames.util.safeApiCall
import javax.inject.Inject

/**
 * Repositorio para manejar las operaciones relacionadas con los juegos.
 * @param gameApi La instancia de GamesApi para realizar llamadas a la API.
 * @see GamesApi
 * @usage Inyectar GamesRepository en ViewModels o componentes de UI para acceder a datos de juegos.
 */
class GamesRepository @Inject  constructor(
  private val gameApi: GamesApi
) {

  /**
   * Obtiene la lista de juegos desde la API.
   * @return Un objeto Resource que contiene la lista de juegos o un mensaje de error.
   */
  suspend fun getGames(): Resource<List<GameList>?> {
    return safeApiCall { gameApi.getGames()?.results ?: emptyList() }
  }

}