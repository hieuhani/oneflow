package vn.periscope.ports.variant.out

import vn.periscope.ports.variant.models.Variant

interface UpdateVariantEntryPoint {
    fun update(id: Long, variant: Variant): Variant
}