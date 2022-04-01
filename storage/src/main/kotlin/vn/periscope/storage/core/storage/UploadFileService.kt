package vn.periscope.storage.core.storage

import io.minio.MinioClient
import io.minio.PutObjectArgs
import vn.periscope.storage.ports.storage.UploadFileUseCase
import java.io.InputStream
import java.nio.file.Path

class UploadFileService(
    private val minioClient: MinioClient,
    private val minioConfig: MinioConfigProperties,
) : UploadFileUseCase {
    override fun upload(path: Path, file: InputStream) {
        val args = PutObjectArgs
            .builder()
            .`object`(path.toString())
            .stream(file, file.available().toLong(), -1)
            .bucket(minioConfig.bucket)
            .build()

        minioClient.putObject(args)
    }
}