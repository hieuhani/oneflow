package vn.periscope.ports.models

data class BrandEntry(
    val name: String,
    val logoId: Long = 0,
    val countryId: Long = 0
)
