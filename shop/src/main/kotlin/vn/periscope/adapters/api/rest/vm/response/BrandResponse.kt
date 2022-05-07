package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import vn.periscope.core.domain.Brand

@Serializable
data class BrandResponse(
    val id: Long,
    val name: String,
    val logoId: Long,
    val countryId: Long,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    companion object {
        fun fromBrand(brand: Brand) = with(brand) {
            BrandResponse(
                id = brand.id,
                name = brand.name,
                logoId = brand.logoId,
                countryId = brand.countryId,
                createdAt = brand.createdAt,
                updatedAt = brand.updatedAt
            )
        }
    }
}
