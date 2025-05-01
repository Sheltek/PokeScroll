package com.cshelton.pokescroll.models

import kotlinx.serialization.Serializable

@Serializable
data class PokeList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokeListItem>
)