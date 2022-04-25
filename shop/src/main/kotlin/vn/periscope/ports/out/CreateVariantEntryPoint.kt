package vn.periscope.ports.out

import vn.periscope.ports.models.VariantEntry

interface CreateVariantEntryPoint {
    fun create(variant: VariantEntry): VariantEntry
}