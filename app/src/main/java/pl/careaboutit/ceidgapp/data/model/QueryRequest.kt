package pl.careaboutit.ceidgapp.data.model

data class QueryRequest(
    val nip: String? = null,
    val regon: String? = null,
    val imie: String? = null,
    val nazwisko: String? = null,
    val ulica: String? = null,
    val budynek: String? = null,
    val lokal: String? = null,
    val miasto: String? = null,
    val wojewodztwo: String? = null,
    val powiat: String? = null,
    val gmina: String? = null,
    val kod: String? = null,
    val pkd: String? = null,
    val dataod: String? = null,
    val datado: String? = null,
    val status: String? = null
)