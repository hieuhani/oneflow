package vn.periscope.ports.models

import kotlinx.datetime.Instant

data class BrandEntry(
    val name: String,
    val shortName: String,
    val logoId: Long = 0,
    val countryId: Long = 0
)