package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.Variant

interface SearchVariantEntryPoint {
    fun search(): List<Variant>
}