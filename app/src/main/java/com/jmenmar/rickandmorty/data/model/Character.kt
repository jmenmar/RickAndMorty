package com.jmenmar.rickandmorty.data.model

data class Character(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val gender: String,
    val type: String,
    val species: String
)
