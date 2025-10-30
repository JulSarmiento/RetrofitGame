package com.julhdev.retrofitgames.viewmodel

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

@HiltViewModel
class GamesViewModel @Inject constructor(
  private val repository: GamesRepository
) : ViewModel() {

  private val _games = MutableStateFlow<List<GameList>>(emptyList())
  val games = _games.asStateFlow()

  init {
    fetchGames()

  }

  private fun fetchGames() {
    viewModelScope.launch(Dispatchers.IO) {
      val result = repository.getGames()

      when (result) {
        is Resource.Success -> _games.value = result.data ?: emptyList()
        is Resource.Error -> {
          print(result.message)
          _games.value = result.data ?: emptyList()
        }
        is Resource.Loading -> {
          print(result)
        }
      }
    }
  }
}