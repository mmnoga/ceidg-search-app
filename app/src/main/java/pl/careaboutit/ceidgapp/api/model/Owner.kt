package pl.careaboutit.ceidgapp.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    val imie: String,
    val nazwisko: String,
    val nip: String,
    val regon: String
)