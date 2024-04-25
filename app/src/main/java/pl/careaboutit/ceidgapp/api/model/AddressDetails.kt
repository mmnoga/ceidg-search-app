package pl.careaboutit.ceidgapp.api.model

import kotlinx.serialization.Serializable

@Serializable
data class AddressDetails(
    val ulica: String,
    val budynek: String,
    val miasto: String,
    val wojewodztwo: String,
    val powiat: String,
    val gmina: String,
    val kraj: String,
    val kod: String,
    val terc: String,
    val simc: String
)