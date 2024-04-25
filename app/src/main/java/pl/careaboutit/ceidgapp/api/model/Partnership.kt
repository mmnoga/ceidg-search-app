package pl.careaboutit.ceidgapp.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Partnership(
    val nip: String,
    val regon: String
)
