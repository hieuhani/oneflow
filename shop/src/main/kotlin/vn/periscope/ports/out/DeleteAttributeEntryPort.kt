package vn.periscope.ports.out

interface DeleteAttributeEntryPort {
    fun delete(id: Long): Boolean
}