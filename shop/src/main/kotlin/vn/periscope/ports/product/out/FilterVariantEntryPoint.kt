package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.Variant

interface FilterVariantEntryPoint {
    fun filter(): List<Variant>
}