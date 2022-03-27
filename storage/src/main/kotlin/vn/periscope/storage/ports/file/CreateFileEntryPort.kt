package vn.periscope.storage.ports.file

import vn.periscope.storage.ports.file.models.FileEntry

interface CreateFileEntryPort {
    fun createFile(fileEntry: FileEntry): FileEntry
}