package pl.careaboutit.ceidgapp.utils

import pl.careaboutit.ceidgapp.data.model.DynamicRequest
import pl.careaboutit.ceidgapp.data.model.QueryRequest

fun buildQueryParamsFromObject(obj: Any): String {
    val fields = obj::class.java.declaredFields
    val params = fields.joinToString("&") { field ->
        field.isAccessible = true
        "${field.name}=${field.get(obj)}"
    }
    return params
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