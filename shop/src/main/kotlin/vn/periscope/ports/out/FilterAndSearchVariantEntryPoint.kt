package vn.periscope.ports.out

import vn.periscope.ports.models.VariantEntry

interface FilterAndSearchVariantEntryPoint {
    fun filter(): List<VariantEntry>
}