package vn.periscope.cms.ports.taxonomyterm.models

data class TaxonomyTermEntry(
    val id: Long? = null,
    val name: String,
    val machineName: String,
    val description: String,
    val taxonomyId: Long,
    val parentId: Long? = null,
)