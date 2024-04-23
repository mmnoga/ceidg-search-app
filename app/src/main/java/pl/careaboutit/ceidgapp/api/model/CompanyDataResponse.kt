package pl.careaboutit.ceidgapp.api.model

import kotlinx.serialization.Serializable

@Serializable
data class CompanyDataResponse(
    val firmy: List<Company>,
    val count: Int,
    val links: Links,
    val properties: Properties
) {
    constructor() : this(
        emptyList(),
        0,
        Links("", "", "", "", ""),
        Properties("", "", "", "", "")
    )
}