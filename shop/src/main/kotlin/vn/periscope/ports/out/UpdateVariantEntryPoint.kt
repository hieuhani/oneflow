package vn.periscope.ports.out

import vn.periscope.ports.models.VariantEntry

interface UpdateVariantEntryPoint {
    fun update(id: Long, variant: VariantEntry): VariantEntry
}