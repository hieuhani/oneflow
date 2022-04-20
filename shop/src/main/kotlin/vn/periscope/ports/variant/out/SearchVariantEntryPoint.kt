package vn.periscope.ports.variant.out

import vn.periscope.ports.variant.models.Variant

interface SearchVariantEntryPoint {
    fun search(): List<Variant>
}