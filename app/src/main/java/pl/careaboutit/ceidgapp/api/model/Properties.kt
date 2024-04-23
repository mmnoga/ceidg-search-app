package pl.careaboutit.ceidgapp.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properties(
    @SerialName("dc:title") val dcTitle: String,
    @SerialName("dc:description") val dcDescription: String,
    @SerialName("dc:language") val dcLanguage: String,
    @SerialName("schema:provider") val schemaProvider: String,
    @SerialName("schema:datePublished") val schemaDatePublished: String
)