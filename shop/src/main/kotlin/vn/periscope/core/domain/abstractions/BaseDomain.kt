package vn.periscope.core.domain.abstractions

import kotlinx.datetime.Instant

interface BaseDomain<TID> : Domain<TID> {
    var createdAt: Instant
    var updatedAt: Instant
}