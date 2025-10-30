package com.julhdev.retrofitgames.data.api

import com.julhdev.retrofitgames.data.model.GamesModel
import com.julhdev.retrofitgames.util.Constants.API_KEY
import com.julhdev.retrofitgames.util.Constants.ENDPOINT_GAMES
import retrofit2.http.GET

/**
 * Interfaz de la API para obtener datos relacionados con juegos.
 * Define los endpoints y métodos HTTP para interactuar con la API de juegos.
 * @usage Inyecta esta interfaz en repositorios o view models para acceder a las operaciones de la API.
 */
interface GamesApi {
  @GET(ENDPOINT_GAMES + API_KEY)
  suspend fun getGames(): GamesModel?
}