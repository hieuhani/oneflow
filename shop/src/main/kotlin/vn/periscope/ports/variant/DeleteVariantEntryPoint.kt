package vn.periscope.ports.variant

interface DeleteVariantEntryPoint {
    fun delete(id: Long): Boolean
}