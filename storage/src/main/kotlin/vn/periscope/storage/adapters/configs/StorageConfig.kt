package vn.periscope.storage.adapters.configs

import io.ktor.server.application.*
import vn.periscope.storage.core.storage.MinioConfigProperties

class StorageConfig(
    environment: ApplicationEnvironment
) {
    val minio by lazy {
        val minioConfig = environment.config.config("storage.minio")
        MinioConfigProperties(
            host = minioConfig.property("host").getString(),
            accessKey = minioConfig.property("accessKey").getString(),
            secretKey = minioConfig.property("secretKey").getString(),
            bucket = minioConfig.property("bucket").getString(),
        )
    }
}