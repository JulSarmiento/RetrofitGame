package com.julhdev.retrofitgames.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.julhdev.retrofitgames.data.model.GameList
import com.julhdev.retrofitgames.ui.theme.CustomBlack

/**
 * Top Bar Principal de la App
 * @param title Título a mostrar
 * @param showBackBtn Indica si se muestra el botón de retroceso
 * @param onBackClick Acción al hacer clic en el botón de retroceso
 * @usage MainTopBar(title = "Título", showBackBtn = true, onBackClick = { /* Acción */ })
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
  title: String,
  showBackBtn: Boolean = false,
  onBackClick: () -> Unit = { }
) {
  TopAppBar(
    title = {
      Text(
        text = title,
        color = Color.White,
        fontWeight = FontWeight.ExtraBold
      )
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = CustomBlack
    ),
    navigationIcon = {
      if(showBackBtn) {
        IconButton(
          onClick = { onBackClick() }
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back Button",
            tint = Color.White
          )
        }
      }
    }
  )
}

/**
 * Componente de tarjeta para mostrar información de un juego.
 * @param game Objeto GameList que contiene la información del juego.
 * @param onClick Función lambda que se ejecuta al hacer clic en la tarjeta.
 * @usage CardGame(game = gameObject, onClick = { /* Acción al hacer clic */ })
 */
@Composable
fun CardGame(
  game: GameList,
  onClick: () -> Unit
){
  Card(
    shape = RoundedCornerShape(5.dp),
    modifier = Modifier
      .padding(10.dp)
      .shadow(40.dp)
      .clickable { onClick() }
  ) {
    Column()
    {
      MainImage(image = game.backgroundImage)
      Text(
        text = game.name,
        fontWeight = FontWeight.ExtraBold,
        color = Color.White,
        modifier = Modifier
          .padding(15.dp)
      )
    }
  }
}

/**
 * Componente para mostrar la imagen principal de un juego.
 * @param image URL de la imagen a mostrar.
 * @usage MainImage(image = "https://example.com/image.jpg")
 */
@Composable
fun MainImage(
  image: String
){
  val image = rememberAsyncImagePainter(model = image)
  Image(
    painter = image,
    contentDescription = "Game Image",
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .fillMaxSize()
      .height(250.dp)
  )

}