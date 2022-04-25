package vn.periscope.ports.models

import kotlinx.datetime.Instant


data class CategoryEntry(
    val id: Long,
    val name: String,
    val createdAt: Instant,
    val updatedAt: Instant,
) {

}
