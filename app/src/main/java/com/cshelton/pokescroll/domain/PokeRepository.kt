package com.cshelton.pokescroll.domain

import com.cshelton.pokescroll.models.PokeList
import com.cshelton.pokescroll.network.PokeService
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface PokeRepository {
    suspend fun getPokemonList(limit: Int = 10, offset: Int = 0): Result<PokeList>
}

class PokeRepositoryImpl() : PokeRepository, KoinComponent {
    private val pokeService: PokeService by inject()

    // Cache
    private val pokeListCache = mutableMapOf<Pair<Int, Int>, PokeList>()
    private val mutex = Mutex()

    override suspend fun getPokemonList(limit: Int, offset: Int): Result<PokeList> {
        // Check if the data is cached
        val cacheKey = Pair(limit, offset)
        mutex.withLock {
            pokeListCache[cacheKey]?.let { return Result.success(it) }
        }

        // If not cached, fetch from the service
        return pokeService.getPokemonList(limit, offset).onSuccess {
            // Cache the fetched data
            mutex.withLock {
                pokeListCache[cacheKey] = it
            }

        }
    }
}