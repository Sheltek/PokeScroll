package com.cshelton.pokescroll.models

import com.cshelton.pokescroll.ui.widgets.KeyProvider
import kotlinx.serialization.Serializable

@Serializable
data class PokeListItem(
    val name: String,
    val url: String
) : KeyProvider {
    override val key = name
}