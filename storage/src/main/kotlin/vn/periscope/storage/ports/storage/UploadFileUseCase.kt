package vn.periscope.storage.ports.storage

import java.io.InputStream
import java.nio.file.Path

interface UploadFileUseCase {
    fun upload(path: Path, file: InputStream)
}