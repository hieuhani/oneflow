package vn.periscope.ports.models

import vn.periscope.ports.models.audit.CreatedAt
import vn.periscope.ports.models.audit.UpdatedAt
import vn.periscope.ports.models.fieldtype.StringField
import vn.periscope.ports.models.id.LongId

data class AttributeEntry(
    val id: LongId,
    val name: Name,
    val values: Values,
    val createdAt: CreatedAt,
    val updatedAt: UpdatedAt
) {
    data class Name(
        private val name: String
    ) : StringField(name) {

        companion object {
            fun of(name: String) = Name(name)
        }
    }

    data class Values(
        private val values: Set<String>,
        private val removeValues: Set<String>
    ) {
        companion object {
            fun of(values: Set<String>) = Values(values, setOf())
        }
    }
}


