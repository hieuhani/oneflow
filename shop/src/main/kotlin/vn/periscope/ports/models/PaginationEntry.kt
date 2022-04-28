package vn.periscope.ports.models

data class PaginationEntry(
    val page: Long = 1,
    val pageSize: Long = 20,
)
