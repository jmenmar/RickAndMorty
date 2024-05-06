package com.jmenmar.rickandmorty.data.network

import com.jmenmar.rickandmorty.data.model.Character
import com.jmenmar.rickandmorty.domain.model.CharactersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterService @Inject constructor(
    private val api: CharacterClient
) {
    suspend fun getCharacter(id: Int): Character {
        return withContext(Dispatchers.IO) {
            val response = api.getCharacter(id = id)
            response
        }
    }

    suspend fun getCharacters(page: Int?, name: String?): CharactersResponse {
        return withContext(Dispatchers.IO) {
            val response = api.getCharacters(page = page, name = name)
            response
        }
    }
}