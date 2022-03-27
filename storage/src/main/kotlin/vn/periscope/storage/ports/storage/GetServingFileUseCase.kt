package vn.periscope.storage.ports.storage

import java.nio.file.Path

interface GetServingFileUseCase {
    fun getSignedObjectUrl(path: Path, expiry: Int) : String
    fun getPublicImageUrl(path: Path)
}