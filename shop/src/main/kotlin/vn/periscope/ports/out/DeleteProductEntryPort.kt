package vn.periscope.ports.out

interface DeleteProductEntryPort {
    fun delete(id: Long, businessId: Long): Boolean
}