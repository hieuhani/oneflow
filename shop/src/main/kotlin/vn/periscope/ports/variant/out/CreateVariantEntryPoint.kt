package vn.periscope.ports.variant.out

import vn.periscope.ports.variant.models.Variant

interface CreateVariantEntryPoint {
    fun create(variant: Variant): Variant
}