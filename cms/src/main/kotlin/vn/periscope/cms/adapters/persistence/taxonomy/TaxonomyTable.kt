package vn.periscope.cms.adapters.persistence.taxonomy

import org.jetbrains.exposed.dao.id.LongIdTable

object TaxonomyTable : LongIdTable(name = "taxonomies") {
    val name = varchar("name", 255)
    val machineName = varchar("machine_name", 255).uniqueIndex()
    val description = text("description")
}