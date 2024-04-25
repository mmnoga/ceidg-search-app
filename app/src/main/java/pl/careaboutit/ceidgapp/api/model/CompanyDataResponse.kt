package pl.careaboutit.ceidgapp.api.model

import kotlinx.serialization.Serializable

@Serializable
class CompanyDataResponse(
    val firma: List<CompanyDetails>,
    val properties: Properties
) {
    constructor() : this(
        emptyList(),
        Properties("", "", "", "", "")
    )
}