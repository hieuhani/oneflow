package vn.periscope.ports.out

import vn.periscope.ports.models.VariantEntry

interface SearchVariantEntryPoint {
    fun search(): List<VariantEntry>
}