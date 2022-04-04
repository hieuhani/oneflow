package vn.periscope.cms.ports.taxonomy.models

data class TaxonomyEntry(
    val id: Long? = null,
    val name: String,
    val machineName: String,
    val description: String,
)