package com.jmenmar.rickandmorty.data.repository

import com.jmenmar.rickandmorty.data.network.CharacterService
import com.jmenmar.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterService
): CharacterRepository {
    override suspend fun getCharacter(id: Int) = callbackFlow {
        trySend(api.getCharacter(id = id))
        awaitClose()
    }

    override suspend fun getCharacters(page: Int?, name: String?) = callbackFlow {
        trySend(api.getCharacters(page = page, name = name))
        awaitClose()
    }
}