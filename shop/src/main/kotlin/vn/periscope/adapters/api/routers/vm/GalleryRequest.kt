package vn.periscope.adapters.api.routers.vm

@kotlinx.serialization.Serializable
data class GalleryRequest(
    val storeId: Long,
    val default: Boolean,
    val position: Int,
){

}
