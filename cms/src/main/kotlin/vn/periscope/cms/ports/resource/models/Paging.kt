package vn.periscope.cms.ports.resource.models

data class Paging<R>(
    val records: List<R>,
    val limit: Int,
    val offset: Int,
    val totalRecords: Long,
)