package pl.careaboutit.ceidgapp.api.model

import kotlinx.serialization.Serializable

@Serializable
data class CompanyDetails(
    val id: String,
    val nazwa: String,
    val adresDzialalnosci: Address? = null,
    val adresyDzialalnosciDodatkowe: List<Address>? = null,
    val adresKorespondencyjny: Address? = null,
    val wlasciciel: Owner? = null,
    val obywatelstwa: List<Citizenship>? = null,
    val pkd: List<String>? = null,
    val pkdGlowny: String? = null,
    val spolki: List<Partnership>? = null,
    val dataRozpoczecia: String? = null,
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