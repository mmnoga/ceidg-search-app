package pl.careaboutit.ceidgapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CompanyDetailsResponse(
    val firma: List<CompanyDetails>,
    val properties: Properties
)

@Serializable
data class CompanyDetails(
    val id: String,
    val nazwa: String,
    val adresDzialalnosci: AddressDetails,
    val adresyDzialalnosciDodatkowe: List<AddressDetails>? = null,
    val adresKorespondencyjny: AddressDetails? = null,
    val wlasciciel: Owner,
    val obywatelstwa: List<Citizenship>? = null,
    val pkd: List<String>,
    val pkdGlowny: String? = null,
    val spolki: List<Partnership>? = null,
    val dataRozpoczecia: String,
    val dataZawieszenia: String? = null,
    val dataZakonczenia: String? = null,
    val dataWykreslenia: String? = null,
    val dataWznowienia: String? = null,
    val status: String? = null,
    val numerStatusu: Int? = null,
    val telefon: String? = null,
    val email: String? = null,
    val www: String? = null,
    val innaFormaKonaktu: String? = null,
    val wspolnoscMajatkowa: Int? = null,
    val link: String? = null
)

@Serializable
data class AddressDetails(
    val ulica: String? = "",
    val budynek: String? = "",
    val miasto: String? = "",
    val wojewodztwo: String? = "",
    val powiat: String? = "",
    val gmina: String? = "",
    val kraj: String? = "",
    val kod: String? = ""
)

@Serializable
data class Citizenship(
    val symbol: String,
    val kraj: String
)

@Serializable
data class Partnership(
    val nip: String,
    val regon: String
)