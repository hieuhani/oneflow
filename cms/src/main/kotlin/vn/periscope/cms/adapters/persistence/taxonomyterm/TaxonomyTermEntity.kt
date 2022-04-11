package vn.periscope.cms.adapters.persistence.taxonomyterm

data class TaxonomyTermEntity(
    val id: Long? = null,
    val name: String,
    val machineName: String,
    val description: String,
    val taxonomyId: Long,
    val parentId: Long? = null,
)