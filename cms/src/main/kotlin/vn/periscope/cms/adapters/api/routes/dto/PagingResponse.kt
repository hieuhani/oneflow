package vn.periscope.cms.adapters.api.routes.dto

import kotlinx.serialization.Serializable

@Serializable
data class PagingResponse<T>(
    val records: List<T>
)