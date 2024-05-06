package com.jmenmar.rickandmorty.ui.screens.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.jmenmar.rickandmorty.data.model.Character

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterCard(
    character: Character,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier.heightIn(min = 280.dp).clickable { onClick(character.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        GlideImage(
            model = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.FillWidth
        )

        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = character.name,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Status: ${character.status}",
                style = TextStyle(fontSize = 14.sp),
                color = Color.Gray
            )
        }
    }
}