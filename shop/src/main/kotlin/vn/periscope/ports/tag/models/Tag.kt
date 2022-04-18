package vn.periscope.ports.tag.models

import kotlinx.datetime.Instant

data class Tag(
    val id: Long,
    val objectType: ObjectType,
    val objectId: Long,
    val value: String,
    val createdAt: Instant

) {
    enum class ObjectType {
        VARIANT,
        PRODUCT,
        PRICE_POLICY,
        PRICE_LIST,
    }

}
