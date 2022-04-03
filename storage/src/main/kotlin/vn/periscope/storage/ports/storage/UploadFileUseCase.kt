package vn.periscope.storage.ports.storage

import vn.periscope.storage.ports.file.models.FileEntry
import java.io.InputStream
import java.nio.file.Path

interface UploadFileUseCase {
    fun upload(path: Path, file: InputStream, contentType: String)
    suspend fun uploadAndCreate(file: InputStream, entry: FileEntry) : FileEntry
}