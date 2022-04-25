package vn.periscope.ports.product.models

class ProductAttributeEntry private constructor(
    val id: Long, val name: String, val values: Set<String>
) {
    private constructor(builder: Builder) : this(
        builder.id, builder.name, builder.values
    )

    companion object {
        inline fun init(block: ProductAttributeEntry.Builder.() -> Unit) =
            ProductAttributeEntry.Builder().apply(block).build()
    }

    class Builder {
        var id: Long = 0
        var name: String = ""
        var values: Set<String> = setOf()
        fun build() = ProductAttributeEntry(
            id,
            name,
            values,
        )
    }
}

