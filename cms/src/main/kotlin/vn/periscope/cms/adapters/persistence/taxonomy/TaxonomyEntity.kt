package vn.periscope.cms.adapters.persistence.taxonomy

data class TaxonomyEntity(
    val id: Long? = null,
    val name: String,
    val machineName: String,
    val description: String,
)