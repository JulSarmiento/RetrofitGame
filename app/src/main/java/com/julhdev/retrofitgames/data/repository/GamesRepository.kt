package com.julhdev.retrofitgames.data.repository

import com.julhdev.retrofitgames.data.api.GamesApi
import com.julhdev.retrofitgames.data.model.GameList
import com.julhdev.retrofitgames.util.resource.Resource
import com.julhdev.retrofitgames.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repositorio para manejar las operaciones relacionadas con los juegos.
 * @param gameApi La instancia de GamesApi para realizar llamadas a la API.
 * @see GamesApi
 * @usage Inyectar GamesRepository en ViewModels o componentes de UI para acceder a datos de juegos.
 */
class GamesRepository @Inject constructor(
  private val gameApi: GamesApi
) {

  /**
   * Obtiene la lista de juegos desde la API.
   * @return Un objeto Resource que contiene la lista de juegos o un mensaje de error.
   */
  fun getGames(): Flow<Resource<List<GameList>>> = flow {
    emit(Resource.Loading())
    try {
      val response = gameApi.getGames()
      val gamesList: List<GameList> = response?.results ?: emptyList()
      emit(Resource.Success(gamesList))
    } catch (e: Exception) {
      emit(Resource.Error("Error al obtener la lista de juegos: ${e.message ?: "Error desconocido"} (${e::class.simpleName})"))
    }
  }
}

