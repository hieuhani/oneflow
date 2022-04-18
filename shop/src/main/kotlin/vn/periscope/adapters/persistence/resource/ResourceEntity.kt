package vn.periscope.adapters.persistence.resource

interface ResourceEntity<Entry> {
    fun toEntry(): Entry
}