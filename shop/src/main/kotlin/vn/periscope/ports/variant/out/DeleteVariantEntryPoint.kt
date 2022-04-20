package vn.periscope.ports.variant.out

interface DeleteVariantEntryPoint {
    fun delete(id: Long): Boolean
}