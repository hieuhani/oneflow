package vn.periscope.cms.adapters.persistence.taxonomyterm

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import vn.periscope.cms.adapters.persistence.taxonomy.TaxonomyTable

object TaxonomyTermTable : LongIdTable(name = "taxonomy_terms") {
    val name = varchar("name", 255)
    val machineName = varchar("machine_name", 255).uniqueIndex()
    val description = text("description")
    val taxonomyId = long("taxonomy_id").references(TaxonomyTable.id, onDelete = ReferenceOption.CASCADE)
    val parentId = long("parent_id").references(TaxonomyTermTable.id).nullable()
}