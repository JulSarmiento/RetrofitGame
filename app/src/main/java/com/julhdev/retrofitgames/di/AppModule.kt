package com.julhdev.retrofitgames.di

import com.julhdev.retrofitgames.data.api.GamesApi
import com.julhdev.retrofitgames.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * AppModule es un módulo de Dagger Hilt que proporciona dependencias a nivel de aplicación.
 * Incluye provisiones para Retrofit y GameApi.
 * Estas dependencias están en el ámbito de singleton para asegurar una única instancia durante el ciclo de vida de la aplicación.
 * @see Retrofit
 * @see GamesApi
 * @usage Inyecta GameApi en repositorios o view models para acceder a las operaciones de la API.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  /**
   * Proporciona una instancia singleton de Retrofit configurada con la URL base y el convertidor Gson.
   * @return Una instancia de Retrofit.
   */
  @Singleton
  @Provides
  fun providesRetrofit(): Retrofit {
    val logging = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
      .addInterceptor(logging)
      .build()

    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

  }

  /**
   * Proporciona una instancia singleton de GameApi utilizando Retrofit.
   * @param retrofit La instancia de Retrofit utilizada para crear GameApi.
   * @return Una instancia de GameApi.
   */
  @Singleton
  @Provides
  fun providesAPIGames(retrofit: Retrofit): GamesApi {
    return retrofit.create(GamesApi::class.java)
  }
}