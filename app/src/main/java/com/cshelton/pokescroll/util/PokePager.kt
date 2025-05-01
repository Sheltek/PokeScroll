package com.cshelton.pokescroll.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cshelton.pokescroll.domain.PokeRepository
import com.cshelton.pokescroll.models.PokeListItem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class PokePager() : PagingSource<Int, PokeListItem>(), KoinComponent {
    private val pokeRepository: PokeRepository by inject()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokeListItem> {
        val page = params.key ?: 0
        return pokeRepository.getPokemonList(offset = page * params.loadSize, limit = params.loadSize).fold(
            onSuccess = { response ->
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (response.next == null) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PokeListItem>) =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}