package vn.periscope.core.domain

import kotlinx.datetime.Instant

data class Brand (
     val id: Long = 0,
     var name: String,
     var logoId: Long = 0,
     var countryId: Long = 0,
     val createdAt: Instant,
     var updatedAt: Instant,
)