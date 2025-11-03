package com.julhdev.retrofitgames.data.api

import com.julhdev.retrofitgames.data.model.GamesModel
import com.julhdev.retrofitgames.data.model.SingleGameModel
import com.julhdev.retrofitgames.util.Constants.API_KEY
import com.julhdev.retrofitgames.util.Constants.ENDPOINT_GAMES
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz de la API para obtener datos relacionados con juegos.
 * Define los endpoints y métodos HTTP para interactuar con la API de juegos.
 * @usage Inyecta esta interfaz en repositorios o view models para acceder a las operaciones de la API.
 */
interface GamesApi {
  /**
   * Obtiene una lista de juegos desde la API.
   * @return Un objeto [GamesModel] que contiene la lista de juegos o null si no se obtienen datos.
   */
  @GET(ENDPOINT_GAMES)
  suspend fun getGames(): GamesModel?

  /**
   * Obtiene los detalles de un juego específico por su ID.
   * @param id El ID del juego a obtener.
   * @return Un objeto [SingleGameModel] que contiene los detalles del juego o null si no se encuentra el juego.
   */
  @GET("${ENDPOINT_GAMES}/{id}")
  suspend fun getGameById(@Path(value = "id") id: Int): SingleGameModel?
}