package vn.periscope.ports.out

import vn.periscope.ports.models.VariantEntry

interface FindVariantEntryPort {
    fun filter(): List<VariantEntry>
}