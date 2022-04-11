package vn.periscope.cms.ports.content.output

interface DeleteContentEntryPort {
    fun delete(id: Long): Boolean
}