package com.jmenmar.rickandmorty.domain.usecases

import com.jmenmar.rickandmorty.data.model.Character
import com.jmenmar.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val characterRepo: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Flow<Character> {
        return characterRepo.getCharacter(id = id)
    }
}