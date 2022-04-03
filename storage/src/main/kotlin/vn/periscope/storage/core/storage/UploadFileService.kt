package vn.periscope.storage.core.storage

import io.minio.MinioClient
import io.minio.PutObjectArgs
import vn.periscope.storage.ports.TransactionService
import vn.periscope.storage.ports.file.CreateFileEntryPort
import vn.periscope.storage.ports.file.models.FileEntry
import vn.periscope.storage.ports.storage.UploadFileUseCase
import java.io.InputStream
import java.nio.file.Path

class UploadFileService(
    private val transactionService: TransactionService,
    private val minioClient: MinioClient,
    private val minioConfig: MinioConfigProperties,
    private val createFileEntryPort: CreateFileEntryPort,
) : UploadFileUseCase {
    override fun upload(path: Path, file: InputStream, contentType: String) {
        val args = PutObjectArgs
            .builder()
            .`object`(path.toString())
            .stream(file, -1, 10485760)
            .contentType(contentType)
            .bucket(minioConfig.bucket)
            .build()

        minioClient.putObject(args)
    }

    override suspend fun uploadAndCreate(file: InputStream, entry: FileEntry) = transactionService.transaction {
        val path = Path.of(entry.serviceName, entry.entityType, entry.entityId.toString(), entry.fileName)

        upload(path, file, entry.contentType)

        createFileEntryPort.createFile(entry)
    }
}