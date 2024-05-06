package com.jmenmar.rickandmorty.domain.repository

import com.jmenmar.rickandmorty.data.model.Character
import com.jmenmar.rickandmorty.domain.model.CharactersResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacter(id: Int): Flow<Character>
    suspend fun getCharacters(page: Int? = null, name: String? = null): Flow<CharactersResponse>
}