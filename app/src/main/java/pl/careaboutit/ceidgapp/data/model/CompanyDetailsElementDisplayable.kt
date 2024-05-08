package pl.careaboutit.ceidgapp.data.model

data class CompanyDetailsElementDisplayable(
    val id: String,
    val nazwa: String,
    val adresDzialalnosci: AddressDetails,
    val wlasciciel: Owner,
    val pkd: List<String>,
    val pkdGlowny: String,
    val dataRozpoczecia: String,
    val status: String,
    val email: String,
    val www: String,
    val telefon: String,
    val innaFormaKonaktu: String,
    val dataZawieszenia: String,
    val dataWznowienia: String,
    val dataZakonczenia: String,
    val dataWykreslenia: String,
    val wspolnoscMajatkowa: Int,
    val obywatelstwa: List<Citizenship>,
    val adresyDzialalnosciDodatkowe: List<AddressDetails>,
    val adresKorespondencyjny: AddressDetails
)

fun CompanyDetails.toDisplayable(): CompanyDetailsElementDisplayable {
    return CompanyDetailsElementDisplayable(
        id = id,
        nazwa = nazwa,
        adresDzialalnosci = adresDzialalnosci,
        wlasciciel = wlasciciel,
        pkd = pkd,
        pkdGlowny = pkdGlowny ?: "",
        dataRozpoczecia = dataRozpoczecia,
        status = status ?: "",
        email = email ?: "",
        www = www ?: "",
        telefon = telefon ?: "",
        innaFormaKonaktu = innaFormaKonaktu ?: "",
        dataZawieszenia = dataZawieszenia ?: "",
        dataWznowienia = dataWznowienia ?: "",
        dataZakonczenia = dataZakonczenia ?: "",
        dataWykreslenia = dataWykreslenia ?: "",
        wspolnoscMajatkowa = wspolnoscMajatkowa ?: 2,
        obywatelstwa = obywatelstwa ?: emptyList(),
        adresyDzialalnosciDodatkowe = adresyDzialalnosciDodatkowe ?: emptyList(),
        adresKorespondencyjny = adresKorespondencyjny ?: AddressDetails()
    )
}