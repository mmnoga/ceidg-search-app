package pl.careaboutit.ceidgapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyListResponse(
    val firmy: List<Company>,
    val count: Int,
    val links: Links,
    val properties: Properties
)

@Serializable
data class Links(
    val next: String,
    val prev: String,
    val self: String,
    val first: String,
    val last: String
)

@Serializable
data class Properties(
    @SerialName("dc:title") val dcTitle: String,
    @SerialName("dc:description") val dcDescription: String,
    @SerialName("dc:language") val dcLanguage: String,
    @SerialName("schema:provider") val schemaProvider: String,
    @SerialName("schema:datePublished") val schemaDatePublished: String
)

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

@Serializable
data class Address(
    val ulica: String? = null,
    val budynek: String? = null,
    val miasto: String? = null,
    val wojewodztwo: String? = null,
    val powiat: String? = null,
    val gmina: String? = null,
    val kraj: String? = null,
    val kod: String? = null,
    val terc: String? = null,
    val simc: String? = null,
    val ulic: String? = null
)

@Serializable
data class Owner(
    val imie: String,
    val nazwisko: String,
    val nip: String,
    val regon: String
)