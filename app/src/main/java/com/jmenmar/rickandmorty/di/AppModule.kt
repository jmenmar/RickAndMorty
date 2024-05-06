package com.jmenmar.rickandmorty.di

import com.jmenmar.rickandmorty.core.Endpoints.BASE_URL
import com.jmenmar.rickandmorty.data.network.CharacterClient
import com.jmenmar.rickandmorty.data.network.CharacterService
import com.jmenmar.rickandmorty.data.repository.CharacterRepositoryImpl
import com.jmenmar.rickandmorty.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiCharacter(retrofit: Retrofit): CharacterClient {
        return retrofit.create(CharacterClient::class.java)
    }

    @Singleton
    @Provides
    fun provideCharacterRepository(
        characterService: CharacterService
    ): CharacterRepository = CharacterRepositoryImpl(characterService)
}