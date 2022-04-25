package vn.periscope.core.domain

import kotlinx.datetime.Instant
import vn.periscope.share.statics.CategoryStatus
import vn.periscope.share.statics.CategoryTaxonomy
import java.util.UUID

data class Category(
    var nid: UUID,
    var businessId: Long,
    var taxonomy: CategoryTaxonomy,
    var name: String,
    var slug: String,
    var status: CategoryStatus,
    var parentId: UUID,
    var createdAt: Instant,
    var updatedAt: Instant,
)
