package pl.careaboutit.ceidgapp.utils

import pl.careaboutit.ceidgapp.data.model.DynamicRequest
import pl.careaboutit.ceidgapp.data.model.QueryRequest
import pl.careaboutit.ceidgapp.ui.viewmodel.FormState

fun buildQueryParamsFromObject(obj: FormState): String {
    val fields = obj.fields.toMutableMap()

    val statusParams = getStatusParams(fields)
    val otherParams = getOtherParams(fields)

    return listOf(statusParams, otherParams)
        .filter { it.isNotEmpty() }
        .joinToString("&")
}

private fun getStatusParams(fields: MutableMap<String, Any>): String {
    return fields["status"]?.let { status ->
        when (status) {
            is List<*> -> {
                val statusList = status.filterIsInstance<Status>()
                if (statusList.isNotEmpty()) {
                    statusList.joinToString("&") { "status=${it.name}" }
                } else {
                    ""
                }
            }

            else -> ""
        }
    } ?: ""
}

private fun getOtherParams(fields: MutableMap<String, Any>): String {
    return fields.entries
        .filter {
            it.key != "status" &&
                    it.value != "" &&
                    !(it.key == "wojewodztwo" && it.value == Voivodeship.BRAK)
        }
        .joinToString("&") { (key, value) ->
            "$key=$value"
        }
}

fun parseDynamicQueryParams(queryParams: String): QueryRequest {
    val params = queryParams.split("&")
    val paramMap = mutableMapOf<String, String?>()

    for (param in params) {
        val pair = param.split("=")
        val key = pair[0]
        val value = if (pair.size > 1) pair[1] else null
        paramMap[key] = value
    }

    return QueryRequest(
        nip = paramMap["nip"],
        regon = paramMap["regon"],
        imie = paramMap["imie"],
        nazwisko = paramMap["nazwisko"],
        ulica = paramMap["ulica"],
        budynek = paramMap["budynek"],
        lokal = paramMap["lokal"],
        miasto = paramMap["miasto"],
        wojewodztwo = paramMap["wojewodztwo"],
        powiat = paramMap["powiat"],
        gmina = paramMap["gmina"],
        kod = paramMap["kod"],
        pkd = paramMap["pkd"],
        dataod = paramMap["dataod"],
        datado = paramMap["datado"],
        status = paramMap["status"]
    )
}

fun mapQueryRequestToDynamicRequest(queryRequest: QueryRequest): DynamicRequest {
    return DynamicRequest(
        params = mapOf(
            "nip" to queryRequest.nip.orEmpty(),
            "regon" to queryRequest.regon.orEmpty(),
            "imie" to queryRequest.imie.orEmpty(),
            "nazwisko" to queryRequest.nazwisko.orEmpty(),
            "ulica" to queryRequest.ulica.orEmpty(),
            "budynek" to queryRequest.budynek.orEmpty(),
            "lokal" to queryRequest.lokal.orEmpty(),
            "miasto" to queryRequest.miasto.orEmpty(),
            "wojewodztwo" to queryRequest.wojewodztwo.orEmpty(),
            "powiat" to queryRequest.powiat.orEmpty(),
            "gmina" to queryRequest.gmina.orEmpty(),
            "kod" to queryRequest.kod.orEmpty(),
            "pkd" to queryRequest.pkd.orEmpty(),
            "dataod" to queryRequest.dataod.orEmpty(),
            "datado" to queryRequest.datado.orEmpty(),
            "status" to queryRequest.status.orEmpty()
        )
    )

}