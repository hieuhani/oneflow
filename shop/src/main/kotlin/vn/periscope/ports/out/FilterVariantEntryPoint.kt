package vn.periscope.ports.out

import vn.periscope.ports.models.VariantEntry

interface FilterVariantEntryPoint {
    fun filter(): List<VariantEntry>
}