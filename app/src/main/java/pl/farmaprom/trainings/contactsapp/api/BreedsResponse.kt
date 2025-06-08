package pl.farmaprom.trainings.contactsapp.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedsResponse(
    val data: List<BreedData>,
    val meta: Meta,
    val links: Links
)

@Serializable
data class BreedData(
    val id: String,
    val type: String,
    val attributes: BreedAttributes,
    val relationships: Relationships
)

@Serializable
data class BreedAttributes(
    val name: String,
    val description: String,
    val life: Range,
    @SerialName("male_weight") val maleWeight: Range,
    @SerialName("female_weight") val femaleWeight: Range,
    val hypoallergenic: Boolean
)

@Serializable
data class Range(
    val max: Int,
    val min: Int
)

@Serializable
data class Relationships(
    val group: GroupRelationship
)

@Serializable
data class GroupRelationship(
    val data: GroupData
)

@Serializable
data class GroupData(
    val id: String,
    val type: String
)

@Serializable
data class Meta(
    val pagination: Pagination
)

@Serializable
data class Pagination(
    val current: Int,
    val next: Int?,
    val last: Int,
    val records: Int
)

@Serializable
data class Links(
    val self: String,
    val current: String,
    val next: String?,
    val last: String
)
