package com.cshelton.pokescroll.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun <T : Any> InfiniteScrollingWidget(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    pager: Pager<Int, T>,
    content: @Composable LazyListScope.(item: T) -> Unit
) {
    val lazyPagingItems: LazyPagingItems<T> = pager.flow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding
    ) {
        // Initial loading message
        val state = lazyPagingItems.loadState.refresh
        if (state == LoadState.Loading) {
            item {
                Text(
                    text = "Waiting for items to load from the backend",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        // Loaded Data
        items(
            count = lazyPagingItems.itemCount,
        ) { index ->
            val item = lazyPagingItems[index]
            if (item != null) {
                this@LazyColumn.content(item)
            }
        }

        if (state is LoadState.Error) {
            item {
                Text(
                    text = "Error: ${state.error.message}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        // Append progress indicator
        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

