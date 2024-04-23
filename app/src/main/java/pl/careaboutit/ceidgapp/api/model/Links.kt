package pl.careaboutit.ceidgapp.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val next: String,
    val prev: String,
    val self: String,
    val first: String,
    val last: String
)