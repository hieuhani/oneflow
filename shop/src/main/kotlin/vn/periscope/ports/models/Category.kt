package vn.periscope.ports.models

import java.time.Instant


data class Category(
    val id: Long,
    val name: String,
    val createdAt: Instant,
    val updatedAt: Instant,
) {

}
