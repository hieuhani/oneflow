package vn.periscope.cms.adapters.persistence.resource

interface ResourceEntity<Entry> {
    fun toEntry(): Entry
}