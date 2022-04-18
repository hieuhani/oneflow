package vn.periscope.ports.category.models

import kotlinx.datetime.Instant


data class Category(
    val id: Long,
    val name: String,
    val createdAt: Instant,
    val updatedAt: Instant,
) {

}
