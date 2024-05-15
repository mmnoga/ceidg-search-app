package pl.careaboutit.ceidgapp.data.model

data class CompanyElementDisplayable(
    val id: String,
    val nazwa: String,
    val wlasciciel: Owner,
    val status: String,
    val miasto: String
)

fun Company.toDisplayable(): CompanyElementDisplayable {
    return CompanyElementDisplayable(
        id = id,
        nazwa = nazwa,
        wlasciciel = wlasciciel!!,
        status = status ?: "",
        miasto = adresDzialalnosci?.miasto ?: ""
    )
}