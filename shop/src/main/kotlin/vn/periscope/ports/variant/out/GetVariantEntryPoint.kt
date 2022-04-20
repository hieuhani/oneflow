package vn.periscope.ports.variant.out

import vn.periscope.ports.variant.models.Variant

interface GetVariantEntryPoint {
    fun findById(id: Long): Variant
}