package vn.periscope.cms.ports.resource.models

abstract class FilterEntry(
    open val limit: Int = 80,
    open val offset: Int = 0,
) {
    object DefaultFilterEntry : FilterEntry()
}

