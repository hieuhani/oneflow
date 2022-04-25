package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.Variant

interface GetVariantEntryPoint {
    fun findById(id: Long): Variant
}