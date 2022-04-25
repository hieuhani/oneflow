package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.Variant

interface UpdateVariantEntryPoint {
    fun update(id: Long, variant: Variant): Variant
}