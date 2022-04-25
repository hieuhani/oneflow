package vn.periscope.ports.out

interface DeleteVariantEntryPoint {
    fun delete(id: Long): Boolean
}