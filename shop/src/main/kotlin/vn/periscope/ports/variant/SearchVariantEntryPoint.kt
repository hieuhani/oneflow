package vn.periscope.ports.variant

import vn.periscope.ports.variant.models.Variant

interface SearchVariantEntryPoint {
    fun search(): List<Variant>
}