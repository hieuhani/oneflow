package vn.periscope.id.adapters.persistence.business

data class BusinessEntity(
    val id: Long,
    val name: String,
    val slug: String,
    val description: String,
)