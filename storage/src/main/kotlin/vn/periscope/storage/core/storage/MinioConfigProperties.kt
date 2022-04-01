package vn.periscope.storage.core.storage

data class MinioConfigProperties(
    val host: String,
    val accessKey: String,
    val secretKey: String,
    val bucket: String,
)