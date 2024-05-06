package com.jmenmar.rickandmorty.data.network

import com.jmenmar.rickandmorty.core.Endpoints.CHARACTERS
import com.jmenmar.rickandmorty.data.model.Character
import com.jmenmar.rickandmorty.domain.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterClient {
    @GET("$CHARACTERS/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character

    @GET("$CHARACTERS/")
    suspend fun getCharacters(@Query("page") page: Int?, @Query("name") name: String?): CharactersResponse
}