package vn.periscope.ports.variant

import vn.periscope.ports.variant.models.Variant

interface FilterVariantEntryPoint {
    fun filter(): List<Variant>
}