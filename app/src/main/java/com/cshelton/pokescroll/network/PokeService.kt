package com.cshelton.pokescroll.network

import com.cshelton.pokescroll.models.PokeList
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface PokeService {
    suspend fun getPokemonList(limit: Int = 10, offset: Int = 0): Result<PokeList>
}

class PokeServiceImpl() : PokeService, KoinComponent {
    private val client: HttpClient by inject()

    override suspend fun getPokemonList(limit: Int, offset: Int): Result<PokeList> = runCatching {
        client.get("https://pokeapi.co/api/v2/pokemon?limit=$limit&offset=$offset").body()
    }
}

