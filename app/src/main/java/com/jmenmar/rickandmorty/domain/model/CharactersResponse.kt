package com.jmenmar.rickandmorty.domain.model

import com.jmenmar.rickandmorty.data.model.Character
import com.jmenmar.rickandmorty.data.model.Info

data class CharactersResponse(
    val info: Info,
    val results: List<Character>
)
