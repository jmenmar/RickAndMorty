package com.jmenmar.rickandmorty.ui.screens.character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val character by viewModel.character.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = character?.name.orEmpty())
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                GlideImage(
                    model = character?.image,
                    contentDescription = character?.name,
                    contentScale = ContentScale.FillWidth
                )

                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Adaptive(128.dp),
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    content = {
                        item {
                            InfoCard(title = "Status", value = character?.status.orEmpty())
                        }
                        item {
                            InfoCard(title = "Gender", value = character?.gender.orEmpty())
                        }
                        item {
                            InfoCard(title = "Species", value = character?.species.orEmpty())
                        }
                        item {
                            InfoCard(title = "Type", value = character?.type.orEmpty())
                        }
                    }
                )
            }
        }
    )
}