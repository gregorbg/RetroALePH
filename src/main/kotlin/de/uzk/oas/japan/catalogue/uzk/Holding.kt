package de.uzk.oas.japan.catalogue.uzk

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Holding(
    @SerialName("holdingid") val id: String,
    @SerialName("Zweigstelle") val branchLibrary: String,
    @SerialName("Signatur") val callNumber: String,
    @SerialName("location_mark") val locationMark: String,
    val availability: String,
    @SerialName("availability_info") val availabilityInfo: List<AvailabilityInfo>? = null,
    @SerialName("unavailability_info") val unavailabilityInfo: List<AvailabilityInfo>? = null,
    @SerialName("storage_id") val storageId: String,
    val barcode: String? = null,
    @SerialName("Standort") val physicalLocation: String,
    @SerialName("department_id") val departmentId: String,
    val department: String,
    @SerialName("boundcollection") val boundCollection: String,
    val storage: String,
    val remark: String? = null,
)
