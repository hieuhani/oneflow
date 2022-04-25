package vn.periscope.ports.models

data class TaxRateLocation(
    val id: Long,
    val businessId: Long,
    val taxId: Long,
    val country: String,
    val state: Set<String>,
    val locationType: LocationType,
    val locationId: Long,
    val locationCode: String,
) {
    enum class LocationType {
        PROVINCE,
        DISTRICT,
        WARD
    }
}
