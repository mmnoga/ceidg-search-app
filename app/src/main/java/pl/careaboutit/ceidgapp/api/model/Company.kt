package pl.careaboutit.ceidgapp.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val id: String,
    val nazwa: String,
    val adresDzialalnosci: Address,
    val wlasciciel: Owner,
    val dataRozpoczecia: String,
    val status: String,
    val link: String
)