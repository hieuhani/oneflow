package vn.periscope.adapters.api.rest.vm.request

@kotlinx.serialization.Serializable
data class GalleryRequest(
    val storeId: Long,
    val default: Boolean,
    val position: Int,
)