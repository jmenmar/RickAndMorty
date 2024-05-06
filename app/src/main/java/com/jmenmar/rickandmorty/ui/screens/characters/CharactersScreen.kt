package com.jmenmar.rickandmorty.ui.screens.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jmenmar.rickandmorty.data.model.Info
import com.jmenmar.rickandmorty.domain.model.CharactersResponse

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val characters by viewModel.characters.collectAsState()
    val search by viewModel.search.collectAsState()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
        ) {
            CharacterSearcher(
                viewModel = viewModel,
                search = search
            )
            
            Spacer(modifier = Modifier.height(12.dp))

            CharactersPreviousNext(
                viewModel = viewModel,
                info = characters?.info
            )
            CharactersContent(
                characters = characters,
                onClick = navigateToDetail
            )
        }
    }
}

@Composable
fun CharacterSearcher(
    viewModel: CharactersViewModel,
    search: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = search,
            placeholder = { Text(text = "Search character") },
            onValueChange = { viewModel.setSearch(it) }
        )

        IconButton(onClick = { viewModel.getCharacters(name = search) }) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        }

        IconButton(
            onClick = {
                viewModel.setSearch("")
                viewModel.getCharacters()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Search"
            )
        }
    }
}

@Composable
fun CharactersPreviousNext(
    viewModel: CharactersViewModel,
    info: Info?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Characters",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )

        IconButton(
            enabled = info?.prev != null,
            onClick = { viewModel.getCharacters(filters = info?.prev) }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Previous page"
            )
        }

        IconButton(
            enabled = info?.next != null,
            onClick = { viewModel.getCharacters(filters = info?.next) }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next page"
            )
        }
    }
}

@Composable
fun CharactersContent(
    characters: CharactersResponse?,
    onClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Adaptive(128.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        content = {
            items(characters?.results.orEmpty()) { character ->
                CharacterCard(
                    character = character,
                    onClick = onClick
                )
            }
        }
    )
}