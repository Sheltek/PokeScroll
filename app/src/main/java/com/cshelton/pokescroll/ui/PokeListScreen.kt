package com.cshelton.pokescroll.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.cshelton.pokescroll.ui.widgets.InfiniteScrollingWidget
import com.cshelton.pokescroll.ui.widgets.PokeListItem
import com.cshelton.pokescroll.util.PokePager

@Composable
fun PokeListScreen(modifier: Modifier = Modifier) {
    val pager = remember {
        Pager(PagingConfig(pageSize = 10)) {
            PokePager()
        }
    }
    Column(modifier = modifier) {
        InfiniteScrollingWidget(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(0.dp),
            pager = pager
        ) { pokemon ->
            Box(modifier = Modifier.fillMaxWidth().height(48.dp)) {

                PokeListItem(pokeListItem = pokemon,
                    modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

