package pl.careaboutit.ceidgapp.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Citizenship(
    val symbol: String,
    val kraj: String
)
