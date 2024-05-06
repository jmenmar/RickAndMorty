package com.jmenmar.rickandmorty.domain.usecases

import com.jmenmar.rickandmorty.domain.model.CharactersResponse
import com.jmenmar.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val characterRepo: CharacterRepository
) {
    suspend operator fun invoke(name: String?, page: Int?): Flow<CharactersResponse> {
        return characterRepo.getCharacters(page = page, name = name)
    }
}