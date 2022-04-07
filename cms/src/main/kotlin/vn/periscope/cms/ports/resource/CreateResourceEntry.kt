package vn.periscope.cms.ports.resource

interface CreateResourceEntry<R> {
    fun createResource(resourceEntry: R): R
}