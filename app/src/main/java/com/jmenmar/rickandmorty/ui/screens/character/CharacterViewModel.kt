package com.jmenmar.rickandmorty.ui.screens.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.rickandmorty.core.Constants.CHAR_KEY
import com.jmenmar.rickandmorty.data.model.Character
import com.jmenmar.rickandmorty.domain.usecases.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase
): ViewModel() {
    private val _character = MutableStateFlow<Character?>(null)
    var character = _character.asStateFlow()

    init {
        savedStateHandle.get<Int>(CHAR_KEY)?.let {  id ->
            getCharacter(id = id)
        }
    }

    private fun getCharacter(id: Int) {
        viewModelScope.launch {
            getCharacterUseCase.invoke(id).collect { response ->
                _character.value = response
            }
        }
    }
}