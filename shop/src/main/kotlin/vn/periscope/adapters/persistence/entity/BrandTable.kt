package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

object BrandTable : LongIdTable(name = "brand") {
    val businessId = long("business_id").index()
    val name = varchar("name", 255)
    val logoId = long("logo_id")
    val countryId = long("country_id")
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
}