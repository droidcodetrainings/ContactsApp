package pl.farmaprom.trainings.contactsapp.dogapi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogApiResponse(
    val data: List<Breed>,
    val meta: Meta,
    val links: Links
)

@Serializable
data class Breed(
    val id: String,
    val type: String,
    val attributes: BreedAttributes,
    val relationships: BreedRelationships
)

@Serializable
data class BreedAttributes(
    val name: String,
    val description: String,
    val life: MinMax,
    @SerialName("male_weight") val maleWeight: MinMax,
    @SerialName("female_weight") val femaleWeight: MinMax,
    val hypoallergenic: Boolean
)

@Serializable
data class MinMax(
    val min: Int,
    val max: Int
)

@Serializable
data class BreedRelationships(
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

