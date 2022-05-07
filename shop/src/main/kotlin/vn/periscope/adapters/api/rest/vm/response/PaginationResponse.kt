package vn.periscope.adapters.api.rest.vm.response

import kotlinx.serialization.Serializable

@Serializable
data class PaginationResponse<E>(
    val records: List<E>,
    val limit: Int = 0,
    val offset: Int = 0,
    val totalRecords: Long = 0,
)
