package com.julhdev.retrofitgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.julhdev.retrofitgames.navigation.NavController
import com.julhdev.retrofitgames.ui.theme.RetrofitGamesTheme
import com.julhdev.retrofitgames.viewmodel.GamesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val viewModel : GamesViewModel by  viewModels()
    setContent {
      RetrofitGamesTheme {
        NavController(viewModel)
      }
    }
  }
}

