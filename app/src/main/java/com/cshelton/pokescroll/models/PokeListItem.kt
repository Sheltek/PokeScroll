package com.cshelton.pokescroll.models

import kotlinx.serialization.Serializable

@Serializable
data class PokeListItem(
    val name: String,
    val url: String
)