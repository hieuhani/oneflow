package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant

@kotlinx.serialization.Serializable
class ProductAttributeResponse private constructor(
    val id: Long?,
    val name: String?,
    val values: Set<String>?,
    val createdAt: Instant?,
    val updatedAt: Instant?,
) {
    private constructor(builder: Builder) : this(
        builder.id,
        builder.name,
        builder.values,
        builder.createdAt,
        builder.updatedAt
    )

    companion object {
        inline fun init(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var id: Long? = null
        var name: String? = null
        var values: Set<String>? = setOf()
        var createdAt: Instant? = null
        var updatedAt: Instant? = null
        fun build() = ProductAttributeResponse(this)
    }
}