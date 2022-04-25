package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant
@kotlinx.serialization.Serializable
class GalleryResponse private constructor(
    val id: Long?,
    val storeId: Long?,
    val default: Boolean?,
    val position: Int?,
    val createdAt: Instant?,
    val updatedAt: Instant?
) {
    private constructor(builder: Builder) : this(
        builder.id,
        builder.storeId,
        builder.default,
        builder.position,
        builder.createdAt,
        builder.updatedAt
    )

    companion object {
        inline fun init(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var id: Long? = null
        var storeId: Long? = null
        var default: Boolean? = null
        var position: Int? = null
        var createdAt: Instant? = null
        var updatedAt: Instant? = null
        fun build() = GalleryResponse(this)
    }
}
