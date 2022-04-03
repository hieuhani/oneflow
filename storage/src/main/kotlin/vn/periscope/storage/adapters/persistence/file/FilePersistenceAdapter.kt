package vn.periscope.storage.adapters.persistence.file

import vn.periscope.storage.ports.file.CreateFileEntryPort
import vn.periscope.storage.ports.file.models.FileEntry

internal class FilePersistenceAdapter(
    private val fileRepository: FileRepository,
) : CreateFileEntryPort {
    override fun createFile(fileEntry: FileEntry): FileEntry {
        return fileRepository.create(fileEntry).toEntry()
    }
}