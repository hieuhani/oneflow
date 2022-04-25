package vn.periscope.ports.product.models

class GalleryEntry private constructor(
    val id: Long,
    val storeId: Long,
    val default: Boolean,
    val position: Int,
) {

    private constructor(builder: Builder) : this(
        builder.id,
        builder.storeId,
        builder.default,
        builder.position,
    )
    companion object {
        inline fun init(block: GalleryEntry.Builder.() -> Unit) = GalleryEntry.Builder().apply(block).build()
    }
     class Builder {
         var id: Long = 0
         var storeId: Long = 0
         var default: Boolean = false
         var position: Int = 0
         fun build() = GalleryEntry(this)
    }
}