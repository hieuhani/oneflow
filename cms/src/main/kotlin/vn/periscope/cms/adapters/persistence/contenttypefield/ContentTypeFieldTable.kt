package vn.periscope.cms.adapters.persistence.contenttypefield

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.dao.id.LongIdTable
import vn.periscope.cms.adapters.persistence.contenttype.ContentTypeTable
import vn.periscope.cms.extensions.jsonb
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldCardinality
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldExtraData
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldType

object ContentTypeFieldTable : LongIdTable("content_type_fields") {
    val label = varchar("label", 255)
    val name = varchar("name", 255)
    val order = integer("order").default(0)
    val required = bool("required").default(false)
    val type = enumerationByName("type", 20, ContentTypeFieldType::class).default(ContentTypeFieldType.UNSPECIFIED)
    val cardinality = enumerationByName("cardinality", 10, ContentTypeFieldCardinality::class).default(
        ContentTypeFieldCardinality.ONE
    )
    val contentTypeId = long("content_type_id").references(ContentTypeTable.id)
    val extraData = jsonb("extra_data", ::stringify, ::parse).nullable()

    init {
        uniqueIndex("content_type_fields_name_contentTypeId_unique", name, contentTypeId)
    }
}

private fun stringify(extraData: ContentTypeFieldExtraData): String {
    return Json.encodeToString(extraData)
}

private fun parse(jsonString: String): ContentTypeFieldExtraData {
    return Json.decodeFromString(jsonString)
}