package com.jmenmar.rickandmorty.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.rickandmorty.domain.model.CharactersResponse
import com.jmenmar.rickandmorty.domain.usecases.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
): ViewModel() {
    private val _loading = MutableStateFlow(false)
    var loading = _loading.asStateFlow()

    private val _characters = MutableStateFlow<CharactersResponse?>(null)
    var characters = _characters.asStateFlow()

    private val _search = MutableStateFlow("")
    var search = _search.asStateFlow()

    init {
        getCharacters()
    }

    fun setSearch(value: String) {
        _search.value = value
    }

    fun getCharacters(name: String? = null, filters: String? = null) {
        val nameValue = filters?.substringAfter("name=", "") ?: name
        val pageValue = filters?.substringAfter("page=")?.substringBefore("&")?.toInt()

        viewModelScope.launch {
            _loading.value = true
            getAllCharactersUseCase.invoke(nameValue, pageValue).collect { response ->
                _characters.value = response
                _loading.value = false
            }
        }
    }
}