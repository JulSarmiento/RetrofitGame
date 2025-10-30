package com.julhdev.retrofitgames.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julhdev.retrofitgames.data.model.GameList
import com.julhdev.retrofitgames.data.repository.GamesRepository
import com.julhdev.retrofitgames.util.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * GamesViewModel es un ViewModel que gestiona el estado y las operaciones relacionadas con la lista de juegos.
 * Interactúa con el GamesRepository para recuperar datos de juegos y expone un StateFlow que representa
 * el estado actual de la lista de juegos, incluyendo estados de carga, éxito y error.
 * @property repository El GamesRepository utilizado para las operaciones de datos.
 * @see GamesRepository
 * @see GameList
 * @see Resource
 * @usage Inyectar GamesViewModel en componentes de UI para observar y manipular datos de juegos.
 */
@HiltViewModel
class GamesViewModel @Inject constructor(
  private val repository: GamesRepository
) : ViewModel() {

  private val _games = MutableStateFlow<Resource<List<GameList>>>(Resource.Loading())
  val games = _games.asStateFlow()

  init {
    fetchGames()

  }

  /**
   * Recupera la lista de juegos del repositorio y actualiza el StateFlow correspondiente.
   * Maneja los estados de éxito, error y carga utilizando la clase Resource.
   * @see GamesRepository
   * @see Resource
   * @usage Llamar a fetchGames() para iniciar la recuperación de datos de juegos.
   */
  private fun fetchGames() {
    viewModelScope.launch(Dispatchers.IO) {
      _games.value = Resource.Loading()
      repository.getGames().collect { result ->
        _games.value = result
      }
    }
  }
}