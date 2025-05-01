package com.cshelton.pokescroll.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cshelton.pokescroll.models.PokeListItem
import com.cshelton.pokescroll.ui.theme.PokeScrollTheme

@Composable
fun PokeListItem(pokeListItem: PokeListItem, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = pokeListItem.name,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF03A9F4)
@Composable
fun PokemonListItemComposablePreview() {
    PokeScrollTheme {
        val previewPokeListItem = PokeListItem(name = "Bulbasaur", url = "")
        PokeListItem(pokeListItem = previewPokeListItem)
    }
}