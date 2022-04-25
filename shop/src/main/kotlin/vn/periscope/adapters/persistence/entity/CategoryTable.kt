package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.CategoryStatus
import vn.periscope.share.statics.CategoryTaxonomy
import java.time.Instant
import java.util.*

object CategoryTable : LongIdTable(name = "category") {
    val nid = uuid("nid").uniqueIndex().default(UUID.randomUUID())
    val businessId = long("business_id")
    val taxonomy = enumerationByName("taxonomy", 32, CategoryTaxonomy::class)
    val name = varchar("name", 255)
    val slug = varchar("slug", 255)
    val status = enumerationByName("status", 32, CategoryStatus::class)
    val parentId = uuid("parent_id").nullable()
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
}