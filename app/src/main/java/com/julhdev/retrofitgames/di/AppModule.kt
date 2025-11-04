package com.julhdev.retrofitgames.di

import com.julhdev.retrofitgames.data.api.GamesApi
import com.julhdev.retrofitgames.util.ApiKeyInterceptor
import com.julhdev.retrofitgames.util.Constants.API_KEY
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
   * Proporciona una instancia singleton de HttpLoggingInterceptor para registrar las solicitudes y respuestas HTTP.
   * @return Una instancia de HttpLoggingInterceptor.
   */
  @Singleton
  @Provides
  fun providesLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  /**
   * Proporciona una instancia singleton de ApiKeyInterceptor para agregar la clave de API a las solicitudes HTTP.
   * @return Una instancia de ApiKeyInterceptor.
   */
  @Singleton
  @Provides
  fun providesApiKeyInterceptor(): ApiKeyInterceptor = ApiKeyInterceptor(API_KEY)

  /**
   * Proporciona una instancia singleton de OkHttpClient configurada con el interceptor de registro.
   * @param loggingInterceptor El interceptor de registro HTTP.
   * @return Una instancia de OkHttpClient.
   */
  @Singleton
  @Provides
  fun providesOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    apiKeyInterceptor: ApiKeyInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(apiKeyInterceptor)
      .addInterceptor(loggingInterceptor)
      .build()
  }

  /**
   * Proporciona una instancia singleton de Retrofit configurada con la URL base y el convertidor Gson.
   * @param client La instancia de OkHttpClient utilizada por Retrofit.
   * @return Una instancia de Retrofit.
   * @usage Utilizar esta instancia para crear servicios de API.
   */
  @Singleton
  @Provides
  fun providesRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder().apply {
      baseUrl(BASE_URL)
      client(client)
      addConverterFactory(GsonConverterFactory.create())
    }.build()
  }

  /**
   * Proporciona una instancia singleton de GameApi utilizando Retrofit.
   * @param retrofit La instancia de Retrofit utilizada para crear GamesApi.
   * @return Una instancia de GameApi.
   */
  @Singleton
  @Provides
  fun providesAPIGames(retrofit: Retrofit): GamesApi {
    return retrofit.create(GamesApi::class.java)
  }
}