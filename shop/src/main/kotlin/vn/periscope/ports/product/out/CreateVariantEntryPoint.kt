package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.Variant

interface CreateVariantEntryPoint {
    fun create(variant: Variant): Variant
}