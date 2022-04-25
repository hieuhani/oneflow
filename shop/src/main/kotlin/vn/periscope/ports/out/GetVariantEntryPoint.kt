package vn.periscope.ports.out

import vn.periscope.ports.models.VariantEntry

interface GetVariantEntryPoint {
    fun findById(id: Long): VariantEntry
}